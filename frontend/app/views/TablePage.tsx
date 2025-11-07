import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import Tippy from '@tippyjs/react';
import {
    type Color,
    type CoordinatesFilter, Dragon,
    type DragonCaveFilter,
    type DragonCharacter,
    type DragonFilter,
    type DragonHeadFilter,
    type DragonType,
    type GetDragonsApiArg,
    type Person,
    type PersonFilter,
    type SortingColumn,
    type SortingDirection,
    useGetDragonsQuery, useReassignAndDeleteDragonMutation,
} from "~/gen/types.generated";
import toast from "react-hot-toast";
import {DeleteDragonModal} from "~/components/DeleteDragonModal";
import {UpdateDragonModal} from "~/components/UpdateDragonModal";
import {AgeSumModal} from "~/components/AgeSumModal";
import {MaxTypeDragonModal} from "~/components/MaxTypeDragonModal";
import {DeepestCaveDragonModal} from "~/components/DeepestCaveDragonModal";

const TooltipContent = ({killer}: { killer: Person }) => (
    <div className="space-y-1 text-left">
        {killer.eyeColor && (
            <div><span className="font-semibold">Eye Color:</span> {killer.eyeColor}</div>
        )}
        {killer.hairColor && (
            <div><span className="font-semibold">Hair Color:</span> {killer.hairColor}</div>
        )}
        {killer.location && (
            <div>
                <span className="font-semibold">Location:</span> {killer.location.name || "Unknown"}
                {(killer.location.x !== undefined || killer.location.y !== undefined) &&
                    ` (${killer.location.x ?? "—"}, ${killer.location.y ?? "—"})`}
            </div>
        )}
        {killer.height && (
            <div><span className="font-semibold">Height:</span> {killer.height}</div>
        )}
        {killer.weight && (
            <div><span className="font-semibold">Weight:</span> {killer.weight}</div>
        )}
        {killer.passportID && (
            <div><span className="font-semibold">Passport ID:</span> {killer.passportID}</div>
        )}
    </div>
);

const KillerTooltip = ({killer}: { killer: Person }) => {
    const hasDetails = killer.eyeColor || killer.hairColor != null || killer.location ||
        killer.height || killer.weight || killer.passportID;

    if (!hasDetails) {
        return <span>{killer.name || "—"}</span>;
    }

    return (
        <Tippy
            content={<TooltipContent killer={killer}/>}
            placement="auto"
            animation="fade"
            delay={[100, 0]}
        >
            <span className="cursor-help border-b border-dotted border-gray-400">
                {killer.name || "—"}
            </span>
        </Tippy>
    );
};

export const TablePage = () => {
    const navigate = useNavigate();
    const [sortColumn, setSortColumn] = useState<SortingColumn>("ID");
    const [sortDirection, setSortDirection] = useState<SortingDirection>("ASC");

    const [filterDragon, setFilterDragon] = useState<DragonFilter>({});

    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize, setPageSize] = useState(10);

    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [dragonToDelete, setDragonToDelete] = useState<Dragon | null>(null);

    const [isUpdateModalOpen, setIsUpdateModalOpen] = useState(false);
    const [dragonToUpdate, setDragonToUpdate] = useState<Dragon | null>(null);

    const [isAgeSumModalOpen, setIsAgeSumModalOpen] = useState(false);
    const [isMaxTypeModalOpen, setIsMaxTypeModalOpen] = useState(false);
    const [isDeepestCaveModalOpen, setIsDeepestCaveModalOpen] = useState(false);

    const [reassignAndDelete, { isLoading: isDeleting }] = useReassignAndDeleteDragonMutation();

    const hasActiveFilters = Object.keys(filterDragon).some(key => {
        const value = filterDragon[key as keyof DragonFilter];
        return value !== undefined && value !== null && value !== "";
    });

    const getDragonApiArg: GetDragonsApiArg = {
        body: {
            sorting: [{column: sortColumn, direction: sortDirection}],
            dragon: hasActiveFilters ? filterDragon : undefined,
            pagination: {
                page: currentPage,
                size: pageSize,
            },
        },
    };

    const {data, error, isLoading, refetch: refetchDragons} = useGetDragonsQuery(getDragonApiArg);

    useEffect(() => {
        const intervalId = setInterval(refetchDragons, 1000)
        return () => clearInterval(intervalId)
    }, [refetchDragons])

    const handleSort = (column: SortingColumn) => {
        if (sortColumn === column) {
            if (sortDirection === 'ASC') {
                setSortDirection('DESC');
            } else {
                setSortColumn('ID');
                setSortDirection('ASC');
            }
        } else {
            setSortColumn(column);
            setSortDirection("ASC");
        }
    };

    const getSortIndicator = (column: SortingColumn) => {
        if (sortColumn === column) {
            return sortDirection === "ASC" ? " ↑" : " ↓";
        }
        return "";
    };

    const updateFilter = <K extends keyof DragonFilter>(key: K, value: DragonFilter[K] | undefined) => {
        setFilterDragon(prev => {
            const newFilter = {...prev};
            if (value === undefined || value === null || value === "" || (typeof value === 'number' && value === 0)) {
                delete newFilter[key];
            } else {
                newFilter[key] = value;
            }
            return newFilter;
        });
    };

    const clearAllFilters = () => {
        setFilterDragon({});
        setCurrentPage(0);
    };

    const totalPages = data && data.total_page_count ? data.total_page_count : 0;

    const handlePreviousPage = () => {
        setCurrentPage(prev => Math.max(0, prev - 1));
    };

    const handleNextPage = () => {
        setCurrentPage(prev => Math.min(totalPages - 1, prev + 1));
    };

    const handleDeleteClick = (dragon: Dragon) => {
        setDragonToDelete(dragon);
        setIsDeleteModalOpen(true);
    };

    const handleDeleteMaxAge = async () => {
        try {
            const allDragonsResponse = await refetchDragons();
            const allDragons = allDragonsResponse.data?.dragons;
            
            if (!allDragons || allDragons.length === 0) {
                toast.error("No dragons available");
                return;
            }

            const maxAgeDragon = allDragons.reduce((max, dragon) => {
                return dragon.age > max.age ? dragon : max;
            });

            setDragonToDelete(maxAgeDragon);
            setIsDeleteModalOpen(true);
        } catch (error) {
            toast.error("Failed to fetch dragons");
            console.error(error);
        }
    };

    const handleUpdateClick = (dragon: Dragon) => {
        setDragonToUpdate(dragon);
        setIsUpdateModalOpen(true);
    };

    const handleCloseDeleteModal = () => {
        setIsDeleteModalOpen(false);
        setDragonToDelete(null);
        refetchDragons();
    };

    const handleCloseUpdateModal = () => {
        setIsUpdateModalOpen(false);
        setDragonToUpdate(null);
        refetchDragons();
    };

    const handleConfirmDelete = async (newOwnerId: number) => {
        if (!dragonToDelete) return;

        try {
            await reassignAndDelete({
                id: dragonToDelete.id,
                body: {
                    newOwnerId: newOwnerId
                }
            }).unwrap().then(refetchDragons);
            toast.success(`Dragon ${dragonToDelete.name} deleted successfully!`);
            handleCloseDeleteModal();
            refetchDragons();
        } catch (e) {
            const apiError = e as { status: number; data: { message?: string } };
            const errorMessage = apiError.data?.message || "Failed to process request.";
            toast.error(errorMessage);
            console.error(e);
        }
    };

    if (isLoading) return <div className="p-4">Loading...</div>;
    if (error) return <div className="p-4 text-red-600">
        Error loading dragons
        <button
            onClick={clearAllFilters}
            disabled={!hasActiveFilters}
            className="px-4 py-2 border border-gray-300 rounded hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"
        >
            Clear All Filters
        </button>
    </div>;

    return (
        <div className="p-4">
            <div className="flex justify-between items-center mb-4">
                <h1 className="text-2xl font-bold">Dragons</h1>
                <div className="flex gap-3">
                    <button
                        onClick={() => navigate("/create")}
                        className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
                    >
                        Create Dragon
                    </button>
                    <button
                        onClick={() => setIsAgeSumModalOpen(true)}
                        className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700"
                    >
                        Age Sum
                    </button>
                    <button
                        onClick={() => setIsMaxTypeModalOpen(true)}
                        className="px-4 py-2 bg-purple-600 text-white rounded hover:bg-purple-700"
                    >
                        Show with Max Type
                    </button>
                    <button
                        onClick={() => setIsDeepestCaveModalOpen(true)}
                        className="px-4 py-2 bg-teal-600 text-white rounded hover:bg-teal-700"
                    >
                        Show in the Deepest Cave
                    </button>
                    <button
                        onClick={handleDeleteMaxAge}
                        disabled={!data?.dragons || data.dragons.length === 0}
                        className="px-4 py-2 bg-orange-600 text-white rounded hover:bg-orange-700 disabled:opacity-50 disabled:cursor-not-allowed"
                    >
                        Delete with Max Age
                    </button>
                    <button
                        onClick={clearAllFilters}
                        disabled={!hasActiveFilters}
                        className="px-4 py-2 border border-gray-300 rounded hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"
                    >
                        Clear All Filters
                    </button>
                </div>
            </div>

            <div className="overflow-x-auto">
                <table className="min-w-full border-collapse border border-gray-300">
                    <thead className="bg-gray-100">
                    <tr>
                        {([
                                {key: "ID", label: "ID"},
                                {key: "NAME", label: "Name"},
                                {key: "COORDINATES_X", label: "X"},
                                {key: "COORDINATES_Y", label: "Y"},
                                {key: "CREATION_DATE", label: "Creation Date"},
                                {key: "CAVE_DEPTH", label: "Cave Depth"},
                                {key: "CAVE_NUMBER_OF_TREASURES", label: "Number of Treasures"},
                                {key: "PERSON_NAME", label: "Killer"},
                                {key: "AGE", label: "Age"},
                                {key: "COLOR", label: "Color"},
                                {key: "DRAGON_TYPE", label: "Type"},
                                {key: "CHARACTER", label: "Character"},
                                {key: "HEAD_TOOTH_COUNT", label: "Teeth"},
                            ] as { key: SortingColumn; label: string }[]
                        ).map((col) => (
                            <th
                                key={col.key}
                                onClick={() => handleSort(col.key)}
                                className="border border-gray-300 px-4 py-2 cursor-pointer hover:bg-gray-200"
                            >
                                {col.label}
                                {getSortIndicator(col.key)}
                            </th>
                        ))}
                        <th className="border border-gray-300 px-4 py-2">Actions</th>
                    </tr>
                    <tr className="bg-white">
                        <td className="border border-gray-300 px-2 py-2">
                            <input
                                type="number"
                                placeholder="ID"
                                value={filterDragon.id || ""}
                                onChange={(e) => updateFilter('id', e.target.value ? Number(e.target.value) : undefined)}
                                className="w-full px-2 py-1 border border-gray-300 rounded text-sm"
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-2">
                            <input
                                type="text"
                                placeholder="Name"
                                value={filterDragon.name || ""}
                                onChange={(e) => updateFilter('name', e.target.value || undefined)}
                                className="w-full px-2 py-1 border border-gray-300 rounded text-sm"
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-2">
                            <input
                                type="number"
                                placeholder="X"
                                value={filterDragon.coordinates?.x ?? ""}
                                onChange={(e) => updateFilter('coordinates', {
                                    ...filterDragon.coordinates,
                                    x: e.target.value ? Number(e.target.value) : undefined
                                } as CoordinatesFilter)}
                                className="w-full px-2 py-1 border border-gray-300 rounded text-sm"
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-2">
                            <input
                                type="number"
                                placeholder="Y"
                                value={filterDragon.coordinates?.y ?? ""}
                                onChange={(e) => updateFilter('coordinates', {
                                    ...filterDragon.coordinates,
                                    y: e.target.value ? Number(e.target.value) : undefined
                                } as CoordinatesFilter)}
                                className="w-full px-2 py-1 border border-gray-300 rounded text-sm"
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-2">
                            <div className="flex w-full items-center gap-1">
                                <input
                                    type="date"
                                    title="From date"
                                    placeholder="From"
                                    value={filterDragon.creationDateRange?.from?.split('T')[0] || ''}
                                    onChange={(e) => {
                                        const value = e.target.value ? `${e.target.value}T00:00:00Z` : undefined;

                                        updateFilter('creationDateRange', {
                                            ...filterDragon.creationDateRange,
                                            from: value,
                                        });
                                        setCurrentPage(0);
                                    }}
                                    className="w-full min-w-0 px-2 py-1 border border-gray-300 rounded text-sm"
                                />

                                <input
                                    type="date"
                                    title="To date"
                                    placeholder="To"
                                    value={filterDragon.creationDateRange?.to?.split('T')[0] || ''}
                                    onChange={(e) => {
                                        const value = e.target.value ? `${e.target.value}T23:59:59Z` : undefined;

                                        updateFilter('creationDateRange', {
                                            ...filterDragon.creationDateRange,
                                            to: value,
                                        });
                                        setCurrentPage(0);
                                    }}
                                    className="w-full min-w-0 px-2 py-1 border border-gray-300 rounded text-sm"
                                />
                            </div>
                        </td>

                        <td className="border border-gray-300 px-2 py-2">
                            <input
                                type="number"
                                placeholder="Depth"
                                value={filterDragon.cave?.depth ?? ""}
                                onChange={(e) => updateFilter('cave', {
                                    ...filterDragon.cave,
                                    depth: e.target.value ? Number(e.target.value) : undefined
                                } as DragonCaveFilter)}
                                className="w-full px-2 py-1 border border-gray-300 rounded text-sm"
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-2">
                            <input
                                type="number"
                                placeholder="Treasures"
                                value={filterDragon.cave?.numberOfTreasures ?? ""}
                                onChange={(e) => updateFilter('cave', {
                                    ...filterDragon.cave,
                                    numberOfTreasures: e.target.value ? Number(e.target.value) : undefined
                                } as DragonCaveFilter)}
                                className="w-full px-2 py-1 border border-gray-300 rounded text-sm"
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-2">
                            <input
                                type="text"
                                placeholder="Killer"
                                value={filterDragon.killer?.name ?? ""}
                                onChange={(e) => updateFilter('killer', {
                                    ...filterDragon.killer,
                                    name: e.target.value || undefined
                                } as PersonFilter)}
                                className="w-full px-2 py-1 border border-gray-300 rounded text-sm"
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-2">
                            <input
                                type="number"
                                placeholder="Age"
                                value={filterDragon.age || ""}
                                onChange={(e) => updateFilter('age', e.target.value ? Number(e.target.value) : undefined)}
                                className="w-full px-2 py-1 border border-gray-300 rounded text-sm"
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-2">
                            <select
                                value={filterDragon.color || ""}
                                onChange={(e) => updateFilter('color', e.target.value as Color || undefined)}
                                className="w-full px-2 py-1 border border-gray-300 rounded text-sm"
                            >
                                <option value="">All</option>
                                <option value="GREEN">GREEN</option>
                                <option value="RED">RED</option>
                                <option value="WHITE">WHITE</option>
                                <option value="BROWN">BROWN</option>
                            </select>
                        </td>
                        <td className="border border-gray-300 px-2 py-2">
                            <select
                                value={filterDragon.type || ""}
                                onChange={(e) => updateFilter('type', e.target.value as DragonType || undefined)}
                                className="w-full px-2 py-1 border border-gray-300 rounded text-sm"
                            >
                                <option value="">All</option>
                                <option value="WATER">WATER</option>
                                <option value="UNDERGROUND">UNDERGROUND</option>
                                <option value="AIR">AIR</option>
                                <option value="FIRE">FIRE</option>
                            </select>
                        </td>
                        <td className="border border-gray-300 px-2 py-2">
                            <select
                                value={filterDragon.character || ""}
                                onChange={(e) => updateFilter('character', e.target.value as DragonCharacter || undefined)}
                                className="w-full px-2 py-1 border border-gray-300 rounded text-sm"
                            >
                                <option value="">All</option>
                                <option value="CUNNING">CUNNING</option>
                                <option value="WISE">WISE</option>
                                <option value="GOOD">GOOD</option>
                                <option value="CHAOTIC_EVIL">CHAOTIC_EVIL</option>
                                <option value="FICKLE">FICKLE</option>
                            </select>
                        </td>
                        <td className="border border-gray-300 px-2 py-2">
                            <input
                                type="number"
                                placeholder="Teeth"
                                value={filterDragon.head?.toothCount ?? ""}
                                onChange={(e) => updateFilter('head', {
                                    ...filterDragon.head,
                                    toothCount: e.target.value ? Number(e.target.value) : undefined
                                } as DragonHeadFilter)}
                                className="w-full px-2 py-1 border border-gray-300 rounded text-sm"
                            />
                        </td>
                    </tr>
                    </thead>
                    <tbody>
                    {data && data.dragons && data.dragons.length > 0 ? (
                        data.dragons.map((dragon) => {
                            const renderValue = (value: any): string | number | JSX.Element => {
                                if (value === null || value === undefined) return "—";
                                if (typeof value === "object") {
                                    return JSON.stringify(value);
                                }
                                return value;
                            };

                            const renderKillerInfo = () => {
                                if (!dragon.killer) return "—";
                                return <KillerTooltip killer={dragon.killer}/>;
                            };

                            return (
                                <tr key={dragon.id} className="hover:bg-gray-50">
                                    {[
                                        dragon.id,
                                        dragon.name,
                                        dragon.coordinates?.x,
                                        dragon.coordinates?.y,
                                        dragon.creationDate ? new Date(dragon.creationDate).toLocaleDateString() : null,
                                        dragon.cave?.depth,
                                        dragon.cave?.numberOfTreasures,
                                        renderKillerInfo(),
                                        dragon.age,
                                        dragon.color,
                                        dragon.type,
                                        dragon.character,
                                        dragon.head?.toothCount,
                                    ].map((value, idx) => (
                                        <td key={idx} className="border border-gray-300 px-4 py-2">
                                            {typeof value === 'object' && value !== null && 'type' in value ? value : renderValue(value)}
                                        </td>
                                    ))}
                                    <td className="border border-gray-300 px-4 py-2 text-center">
                                        <div className="flex gap-3 justify-center">
                                            <button
                                                onClick={() => handleUpdateClick(dragon)}
                                                className="text-blue-500 hover:text-blue-700 font-semibold"
                                            >
                                                Update
                                            </button>
                                            <button
                                                onClick={() => handleDeleteClick(dragon)}
                                                className="text-red-500 hover:text-red-700 font-semibold"
                                            >
                                                Delete
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            );
                        })
                    ) : (
                        <tr>
                            <td
                                colSpan={13}
                                className="border border-gray-300 px-4 py-8 text-center text-gray-500"
                            >
                                No dragons found
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>

            {data && data.dragons && data.dragons.length > 0 && (
                <div className="mt-4 flex justify-between items-center">
                    <div className="text-gray-600">
                        Total dragons: {data.total_dragons_count}
                    </div>

                    <div className="flex items-center gap-4">
                        <div className="text-gray-600">
                            Page {currentPage + 1} of {totalPages}
                        </div>

                        <div className="flex gap-2">
                            <button
                                onClick={handlePreviousPage}
                                disabled={currentPage === 0}
                                className="px-4 py-2 border border-gray-300 rounded hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"
                            >
                                ← Previous
                            </button>
                            <button
                                onClick={handleNextPage}
                                disabled={currentPage >= totalPages - 1}
                                className="px-4 py-2 border border-gray-300 rounded hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"
                            >
                                Next →
                            </button>
                        </div>

                        <div className="flex items-center gap-2">
                            <label className="text-sm text-gray-600">Items per page:</label>
                            <select
                                value={pageSize}
                                onChange={(e) => {
                                    setPageSize(Number(e.target.value));
                                    setCurrentPage(0);
                                }}
                                className="px-2 py-1 border border-gray-300 rounded"
                            >
                                <option value={5}>5</option>
                                <option value={10}>10</option>
                                <option value={25}>25</option>
                                <option value={50}>50</option>
                                <option value={100}>100</option>
                            </select>
                        </div>
                    </div>
                </div>
            )}
            <DeleteDragonModal
                isOpen={isDeleteModalOpen}
                onClose={handleCloseDeleteModal}
                onConfirm={handleConfirmDelete}
                dragonToDelete={dragonToDelete}
                isLoading={isDeleting}
            />
            <UpdateDragonModal
                isOpen={isUpdateModalOpen}
                onClose={handleCloseUpdateModal}
                dragonToUpdate={dragonToUpdate}
            />
            <AgeSumModal
                isOpen={isAgeSumModalOpen}
                onClose={() => setIsAgeSumModalOpen(false)}
            />
            <MaxTypeDragonModal
                isOpen={isMaxTypeModalOpen}
                onClose={() => setIsMaxTypeModalOpen(false)}
            />
            <DeepestCaveDragonModal
                isOpen={isDeepestCaveModalOpen}
                onClose={() => setIsDeepestCaveModalOpen(false)}
            />
        </div>
    );
};
