import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import {
    useCreateDragonMutation,
    useGetCavesQuery,
    useGetCoordinatesQuery,
    useGetHeadsQuery,
    useGetLocationsQuery,
    useGetPersonsQuery,
} from "~/gen/types.generated";
import type {
    Color,
    DragonCharacter,
    DragonCreate,
    DragonCreateCave,
    DragonCreateCoordinates,
    DragonCreateHead,
    DragonCreatePerson,
    DragonType,
} from "~/gen/types.generated";
import {FormInputString} from "~/components/FormInputString";
import {FormInputInt} from "~/components/FormInputInt";
import {FormInputFloat} from "~/components/FormInputFloat";
import {FormSelect} from "~/components/FormSelect";
import {NestedEntitySelector} from "~/components/NestedEntitySelector";
import {FormSection} from "~/components/FormSection";
import {
    CoordinatesValidation,
    DragonCaveValidation,
    DragonHeadValidation,
    DragonValidation,
    LocationValidation,
    PersonValidation,
} from "~/utils/validation";
import toast from "react-hot-toast";

export const CreateDragonPage = () => {
    const navigate = useNavigate();
    const [createDragon, {isLoading}] = useCreateDragonMutation();
    const {data: coordinates = []} = useGetCoordinatesQuery();
    const {data: caves = []} = useGetCavesQuery();
    const {data: persons = []} = useGetPersonsQuery();
    const {data: heads = []} = useGetHeadsQuery();
    const {data: locations = []} = useGetLocationsQuery();

    const [formData, setFormData] = useState<Partial<DragonCreate>>({
        name: "",
        age: 1,
        color: "GREEN",
        type: "WATER",
    });

    const [createNewCoordinates, setCreateNewCoordinates] = useState(false);
    const [createNewCave, setCreateNewCave] = useState(false);
    const [createNewKiller, setCreateNewKiller] = useState(false);
    const [createNewHead, setCreateNewHead] = useState(false);
    const [createNewPersonLocation, setCreateNewPersonLocation] = useState(false);

    const [newCoordinates, setNewCoordinates] = useState<Partial<DragonCreateCoordinates>>({x: 0, y: 0});
    const [newCave, setNewCave] = useState<Partial<DragonCreateCave>>({depth: 0, numberOfTreasures: 0});
    const [newPerson, setNewPerson] = useState<Partial<DragonCreatePerson>>({
        name: "",
        eyeColor: "GREEN",
        weight: 0,
        passportID: "",
        location: {name: "", x: 0, y: 0}
    });
    const [newHead, setNewHead] = useState<Partial<DragonCreateHead>>({toothCount: 0});

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        const payload = {...formData};

        if (createNewCoordinates) {
            payload.coordinates = newCoordinates as DragonCreateCoordinates;
        }
        if (createNewCave) {
            payload.cave = newCave as DragonCreateCave;
        }
        if (createNewKiller) {
            payload.killer = newPerson as DragonCreatePerson;
        }
        if (createNewHead) {
            payload.head = newHead as DragonCreateHead;
        }

        await createDragon({dragonCreate: payload as DragonCreate})
            .unwrap()
            .then(v => {
                toast.success("Dragon created successfully!");
                navigate("/");
            })
            .catch(e => {
                const errorMessage = e?.data?.message || e?.message || "Failed to create dragon";
                toast.error(errorMessage);
            });
    };

    const handleChange = (field: keyof DragonCreate, value: any) => {
        setFormData(prev => ({...prev, [field]: value}));
    };

    return (
        <div className="p-6 max-w-4xl mx-auto">
            <h1 className="text-2xl font-bold mb-6">Create Dragon</h1>

            <form onSubmit={handleSubmit} className="space-y-6">
                <FormSection title="Basic Information" columns={2}>
                    <FormInputString
                        label="Name"
                        value={formData.name || ""}
                        onChange={(val) => handleChange("name", val)}
                        validation={DragonValidation.name}
                    />
                    <FormInputInt
                        label="Age"
                        value={formData.age || ""}
                        onChange={(val) => handleChange("age", val)}
                        validation={DragonValidation.age}
                    />
                </FormSection>

                <FormSection title="Dragon Characteristics" columns={3}>
                    <FormSelect
                        label="Color"
                        value={formData.color || "GREEN"}
                        onChange={(val) => handleChange("color", val as Color)}
                        options={[
                            {value: "GREEN", label: "Green"},
                            {value: "RED", label: "Red"},
                            {value: "WHITE", label: "White"},
                            {value: "BROWN", label: "Brown"},
                        ]}
                        required
                    />
                    <FormSelect
                        label="Type"
                        value={formData.type || "WATER"}
                        onChange={(val) => handleChange("type", val as DragonType)}
                        options={[
                            {value: "WATER", label: "Water"},
                            {value: "UNDERGROUND", label: "Underground"},
                            {value: "AIR", label: "Air"},
                            {value: "FIRE", label: "Fire"},
                        ]}
                        required
                    />
                    <FormSelect
                        label="Character"
                        value={formData.character || ""}
                        onChange={(val) => handleChange("character", val ? val as DragonCharacter : null)}
                        options={[
                            {value: "", label: "None"},
                            {value: "CUNNING", label: "Cunning"},
                            {value: "WISE", label: "Wise"},
                            {value: "GOOD", label: "Good"},
                            {value: "CHAOTIC_EVIL", label: "Chaotic Evil"},
                            {value: "FICKLE", label: "Fickle"},
                        ]}
                    />
                </FormSection>

                <FormSection title="Location" columns={2}>
                    <NestedEntitySelector
                        label="Coordinates *"
                        isCreatingNew={createNewCoordinates}
                        onToggle={() => {
                            setCreateNewCoordinates(!createNewCoordinates);
                            if (createNewCoordinates) {
                                handleChange("coordinates", undefined);
                            }
                        }}
                        existingEntitySelect={
                            <select
                                required
                                className="w-full border rounded px-3 py-2"
                                value={typeof formData.coordinates === 'object' ? formData.coordinates?.id || "" : formData.coordinates || ""}
                                onChange={(e) => handleChange("coordinates", parseInt(e.target.value))}
                            >
                                <option value="">Select coordinates...</option>
                                {coordinates.map((coordinate) => (
                                    <option key={coordinate.id} value={coordinate.id}>
                                        ID {coordinate.id}: ({coordinate.x}; {coordinate.y})
                                    </option>
                                ))}
                            </select>
                        }
                        newEntityForm={
                            <div className="space-y-2">
                                <FormInputFloat
                                    label="X"
                                    placeholder="X"
                                    value={newCoordinates.x || ""}
                                    onChange={(val) => setNewCoordinates({...newCoordinates, x: val})}
                                    validation={CoordinatesValidation.x}
                                />
                                <FormInputInt
                                    label="Y"
                                    placeholder="Y"
                                    value={newCoordinates.y || ""}
                                    onChange={(val) => setNewCoordinates({...newCoordinates, y: val})}
                                    validation={CoordinatesValidation.y}
                                />
                            </div>
                        }
                    />

                    <NestedEntitySelector
                        label="Cave *"
                        isCreatingNew={createNewCave}
                        onToggle={() => {
                            setCreateNewCave(!createNewCave);
                            if (createNewCave) {
                                handleChange("cave", undefined);
                            }
                        }}
                        existingEntitySelect={
                            <select
                                required
                                className="w-full border rounded px-3 py-2"
                                value={typeof formData.cave === 'object' ? formData.cave?.id || "" : formData.cave || ""}
                                onChange={(e) => handleChange("cave", parseInt(e.target.value))}
                            >
                                <option value="">Select cave...</option>
                                {caves.map((cave) => (
                                    <option key={cave.id} value={cave.id}>
                                        ID {cave.id}: Depth {cave.depth}, Treasures {cave.numberOfTreasures}
                                    </option>
                                ))}
                            </select>
                        }
                        newEntityForm={
                            <div className="space-y-2">
                                <FormInputFloat
                                    label="Depth"
                                    placeholder="Depth"
                                    value={newCave.depth || ""}
                                    onChange={(val) => setNewCave({...newCave, depth: val})}
                                    validation={DragonCaveValidation.depth}
                                />
                                <FormInputInt
                                    label="Number of Treasures"
                                    placeholder="Number of Treasures"
                                    value={newCave.numberOfTreasures || ""}
                                    onChange={(val) => setNewCave({...newCave, numberOfTreasures: val})}
                                    validation={DragonCaveValidation.numberOfTreasures}
                                />
                            </div>
                        }
                    />
                </FormSection>

                <FormSection title="Related Entities (Optional)" columns={2}>
                    <NestedEntitySelector
                        label="Killer"
                        isCreatingNew={createNewKiller}
                        onToggle={() => {
                            setCreateNewKiller(!createNewKiller);
                            if (createNewKiller) {
                                handleChange("killer", null);
                            }
                        }}
                        fullWidthWhenCreating
                        existingEntitySelect={
                            <select
                                className="w-full border rounded px-3 py-2"
                                value={typeof formData.killer === 'object' ? formData.killer?.id || "" : formData.killer || ""}
                                onChange={(e) => handleChange("killer", e.target.value ? parseInt(e.target.value) : null)}
                            >
                                <option value="">None</option>
                                {persons.map((person) => (
                                    <option key={person.id} value={person.id}>
                                        ID {person.id}: {person.name}
                                    </option>
                                ))}
                            </select>
                        }
                        newEntityForm={
                            <div className="grid grid-cols-2 gap-3">
                                <FormInputString
                                    label="Name"
                                    placeholder="Name *"
                                    value={newPerson.name || ""}
                                    onChange={(val) => setNewPerson({...newPerson, name: val})}
                                    validation={PersonValidation.name}
                                />
                                <FormInputString
                                    label="Passport ID"
                                    placeholder="Passport ID *"
                                    value={newPerson.passportID || ""}
                                    onChange={(val) => setNewPerson({...newPerson, passportID: val})}
                                    validation={PersonValidation.passportID}
                                />
                                <FormSelect
                                    label="Eye Color"
                                    value={newPerson.eyeColor || "GREEN"}
                                    onChange={(val) => setNewPerson({...newPerson, eyeColor: val as Color})}
                                    options={[
                                        {value: "GREEN", label: "Eye Color: Green"},
                                        {value: "RED", label: "Eye Color: Red"},
                                        {value: "WHITE", label: "Eye Color: White"},
                                        {value: "BROWN", label: "Eye Color: Brown"},
                                    ]}
                                />
                                <FormSelect
                                    label="Hair Color"
                                    value={newPerson.hairColor || ""}
                                    onChange={(val) => setNewPerson({
                                        ...newPerson,
                                        hairColor: val ? val as Color : null
                                    })}
                                    options={[
                                        {value: "", label: "Hair Color: None"},
                                        {value: "GREEN", label: "Hair Color: Green"},
                                        {value: "RED", label: "Hair Color: Red"},
                                        {value: "WHITE", label: "Hair Color: White"},
                                        {value: "BROWN", label: "Hair Color: Brown"},
                                    ]}
                                />
                                <FormInputFloat
                                    label="Weight"
                                    placeholder="Weight *"
                                    value={newPerson.weight || ""}
                                    onChange={(val) => setNewPerson({...newPerson, weight: val})}
                                    validation={PersonValidation.weight}
                                />
                                <FormInputFloat
                                    label="Height"
                                    placeholder="Height"
                                    value={newPerson.height || ""}
                                    onChange={(val) => setNewPerson({...newPerson, height: val || null})}
                                    validation={PersonValidation.height}
                                />
                                <div className="col-span-2">
                                    <NestedEntitySelector
                                        label="Location"
                                        isCreatingNew={createNewPersonLocation}
                                        onToggle={() => {
                                            setCreateNewPersonLocation(!createNewPersonLocation);
                                            setNewPerson({
                                                ...newPerson,
                                                location: createNewPersonLocation ? null : {name: "", x: 0, y: 0}
                                            });
                                        }}
                                        existingEntitySelect={
                                            <select
                                                className="w-full border rounded px-3 py-2"
                                                value={newPerson.location?.id || ""}
                                                onChange={(e) => {
                                                    const locId = e.target.value ? parseInt(e.target.value) : undefined;
                                                    const loc = locId ? locations.find(l => l.id === locId) : null;
                                                    setNewPerson({...newPerson, location: loc});
                                                }}
                                            >
                                                <option value="">Location: None</option>
                                                {locations.map((loc) => (
                                                    <option key={loc.id} value={loc.id}>
                                                        {loc.name || `ID ${loc.id}`}: ({loc.x}, {loc.y})
                                                    </option>
                                                ))}
                                            </select>
                                        }
                                        newEntityForm={
                                            <div className="space-y-3 p-3 border rounded">
                                                <FormInputString
                                                    label="Location Name"
                                                    placeholder="e.g. Camelot"
                                                    value={typeof newPerson.location === 'object' && newPerson.location && !newPerson.location.id ? newPerson.location.name || "" : ""}
                                                    onChange={(val) => setNewPerson({
                                                        ...newPerson,
                                                        location: {
                                                            ...newPerson.location,
                                                            name: val,
                                                            id: undefined,
                                                            x: newPerson.location?.x ?? 0,
                                                            y: newPerson.location?.y ?? 0
                                                        }
                                                    })}
                                                    validation={LocationValidation.name}
                                                />
                                                <div className="grid grid-cols-2 gap-2">
                                                    <FormInputFloat
                                                        label="Location X"
                                                        placeholder="X"
                                                        value={typeof newPerson.location === 'object' && newPerson.location && !newPerson.location.id ? newPerson.location.x || "" : ""}
                                                        onChange={(val) => setNewPerson({
                                                            ...newPerson,
                                                            location: {
                                                                ...newPerson.location,
                                                                x: val,
                                                                id: undefined,
                                                                y: newPerson.location?.y ?? 0,
                                                                name: newPerson.location?.name ?? ""
                                                            }
                                                        })}
                                                        validation={LocationValidation.x}
                                                    />
                                                    <FormInputFloat
                                                        label="Location Y"
                                                        placeholder="Y"
                                                        value={typeof newPerson.location === 'object' && newPerson.location && !newPerson.location.id ? newPerson.location.y || "" : ""}
                                                        onChange={(val) => setNewPerson({
                                                            ...newPerson,
                                                            location: {
                                                                ...newPerson.location,
                                                                y: val,
                                                                id: undefined,
                                                                x: newPerson.location?.x ?? 0,
                                                                name: newPerson.location?.name ?? ""
                                                            }
                                                        })}
                                                        validation={LocationValidation.y}
                                                    />
                                                </div>
                                            </div>
                                        }
                                    />
                                </div>

                            </div>
                        }
                    />

                    <NestedEntitySelector
                        label="Head"
                        isCreatingNew={createNewHead}
                        onToggle={() => {
                            setCreateNewHead(!createNewHead);
                            if (createNewHead) {
                                handleChange("head", undefined);
                            }
                        }}
                        existingEntitySelect={
                            <select
                                className="w-full border rounded px-3 py-2"
                                value={typeof formData.head === 'object' ? formData.head?.id || "" : formData.head || ""}
                                onChange={(e) => handleChange("head", e.target.value ? parseInt(e.target.value) : undefined)}
                            >
                                <option value="">None</option>
                                {heads.map((head) => (
                                    <option key={head.id} value={head.id}>
                                        ID {head.id}: {head.toothCount} teeth
                                    </option>
                                ))}
                            </select>
                        }
                        newEntityForm={
                            <FormInputInt
                                label=""
                                placeholder="Tooth Count"
                                value={newHead.toothCount || ""}
                                onChange={(val) => setNewHead({...newHead, toothCount: val})}
                                validation={DragonHeadValidation.toothCount}
                            />
                        }
                    />
                </FormSection>

                <div className="pt-2">
                    <button
                        type="submit"
                        disabled={isLoading}
                        className="w-full bg-blue-600 text-white rounded px-4 py-2 hover:bg-blue-700 disabled:bg-gray-400"
                    >
                        {isLoading ? "Creating..." : "Create Dragon"}
                    </button>
                </div>
            </form>
        </div>
    );
}
