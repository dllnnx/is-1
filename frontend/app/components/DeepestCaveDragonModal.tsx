import React, {useMemo} from 'react';
import {useGetDragonsQuery, type Dragon} from '~/gen/types.generated';

interface Props {
    isOpen: boolean;
    onClose: () => void;
}

export const DeepestCaveDragonModal = ({isOpen, onClose}: Props) => {
    const {data: allDragonsData, isLoading, error} = useGetDragonsQuery({
        body: {
            pagination: {size: 10000}
        }
    }, {
        skip: !isOpen,
    });

    const deepestCaveDragon = useMemo(() => {
        if (!allDragonsData?.dragons || allDragonsData.dragons.length === 0) {
            return null;
        }

        return allDragonsData.dragons.reduce((max: Dragon | null, dragon: Dragon) => {
            if (!dragon.cave) return max;
            if (!max || !max.cave) return dragon;
            return dragon.cave.depth > max.cave.depth ? dragon : max;
        }, null as Dragon | null);
    }, [allDragonsData]);

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-black/50 flex justify-center items-center z-50">
            <div className="bg-white p-6 rounded-lg shadow-xl max-w-2xl w-full max-h-[90vh] overflow-y-auto">
                <h2 className="text-xl font-bold mb-4">Dragon in the Deepest Cave</h2>
                
                {isLoading && (
                    <div className="text-gray-600">Loading...</div>
                )}
                
                {error && (
                    <div className="text-red-600">
                        Error loading dragons. Please try again.
                    </div>
                )}
                
                {!isLoading && !error && !deepestCaveDragon && (
                    <div className="text-gray-600">
                        No dragons with caves found.
                    </div>
                )}
                
                {!isLoading && !error && deepestCaveDragon && (
                    <div className="space-y-4">
                        <div className="text-lg font-semibold mb-4">
                            Dragon in the deepest cave:
                        </div>
                        <div className="bg-gray-50 rounded-lg p-4 space-y-3">
                            <div className="grid grid-cols-2 gap-3">
                                <div>
                                    <span className="text-sm font-semibold text-gray-600">ID:</span>
                                    <span className="ml-2 text-gray-800">{deepestCaveDragon.id}</span>
                                </div>
                                <div>
                                    <span className="text-sm font-semibold text-gray-600">Name:</span>
                                    <span className="ml-2 text-gray-800">{deepestCaveDragon.name}</span>
                                </div>
                                <div>
                                    <span className="text-sm font-semibold text-gray-600">Age:</span>
                                    <span className="ml-2 text-gray-800">{deepestCaveDragon.age}</span>
                                </div>
                                <div>
                                    <span className="text-sm font-semibold text-gray-600">Color:</span>
                                    <span className="ml-2 text-gray-800">{deepestCaveDragon.color}</span>
                                </div>
                                <div>
                                    <span className="text-sm font-semibold text-gray-600">Type:</span>
                                    <span className="ml-2 text-gray-800">{deepestCaveDragon.type}</span>
                                </div>
                                {deepestCaveDragon.character && (
                                    <div>
                                        <span className="text-sm font-semibold text-gray-600">Character:</span>
                                        <span className="ml-2 text-gray-800">{deepestCaveDragon.character}</span>
                                    </div>
                                )}
                            </div>
                            
                            {deepestCaveDragon.coordinates && (
                                <div className="border-t pt-3">
                                    <span className="text-sm font-semibold text-gray-600">Coordinates:</span>
                                    <span className="ml-2 text-gray-800">
                                        ({deepestCaveDragon.coordinates.x}, {deepestCaveDragon.coordinates.y})
                                    </span>
                                </div>
                            )}
                            
                            {deepestCaveDragon.cave && (
                                <div className="border-t pt-3">
                                    <span className="text-sm font-semibold text-gray-600">Cave:</span>
                                    <span className="ml-2 text-gray-800 font-semibold">
                                        Depth {deepestCaveDragon.cave.depth}, {deepestCaveDragon.cave.numberOfTreasures} treasures
                                    </span>
                                </div>
                            )}
                            
                            {deepestCaveDragon.killer && (
                                <div className="border-t pt-3">
                                    <span className="text-sm font-semibold text-gray-600">Killer:</span>
                                    <span className="ml-2 text-gray-800">{deepestCaveDragon.killer.name}</span>
                                </div>
                            )}
                            
                            {deepestCaveDragon.head && (
                                <div className="border-t pt-3">
                                    <span className="text-sm font-semibold text-gray-600">Head:</span>
                                    <span className="ml-2 text-gray-800">{deepestCaveDragon.head.toothCount} teeth</span>
                                </div>
                            )}
                            
                            {deepestCaveDragon.creationDate && (
                                <div className="border-t pt-3">
                                    <span className="text-sm font-semibold text-gray-600">Created:</span>
                                    <span className="ml-2 text-gray-800">
                                        {new Date(deepestCaveDragon.creationDate).toLocaleDateString()}
                                    </span>
                                </div>
                            )}
                        </div>
                    </div>
                )}

                <div className="mt-6 flex justify-end">
                    <button
                        onClick={onClose}
                        className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300"
                    >
                        Close
                    </button>
                </div>
            </div>
        </div>
    );
};

