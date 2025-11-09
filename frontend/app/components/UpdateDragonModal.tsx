import React, { useState, useEffect } from 'react';
import type {
  Dragon,
  Color,
  DragonCharacter,
  DragonType,
  DragonCave,
  Coordinates,
} from '~/gen/types.generated';
import {
  useUpdateDragonMutation,
  useGetCoordinatesQuery,
  useGetCavesQuery,
  useGetPersonsQuery,
  useGetHeadsQuery,
  useGetLocationsQuery,
} from '~/gen/types.generated';
import { FormInputString } from '~/components/FormInputString';
import { FormInputInt } from '~/components/FormInputInt';
import { FormInputFloat } from '~/components/FormInputFloat';
import { FormSelect } from '~/components/FormSelect';
import { NestedEntitySelector } from '~/components/NestedEntitySelector';
import { FormSection } from '~/components/FormSection';
import {
  CoordinatesValidation,
  DragonCaveValidation,
  DragonHeadValidation,
  DragonValidation,
  LocationValidation,
  PersonValidation,
} from '~/utils/validation';
import toast from 'react-hot-toast';

interface Props {
  isOpen: boolean;
  onClose: () => void;
  dragonToUpdate: Dragon | null;
}

export const UpdateDragonModal = ({
  isOpen,
  onClose,
  dragonToUpdate,
}: Props) => {
  const [updateDragon, { isLoading }] = useUpdateDragonMutation();
  const { data: coordinates = [] } = useGetCoordinatesQuery();
  const { data: caves = [] } = useGetCavesQuery();
  const { data: persons = [] } = useGetPersonsQuery();
  const { data: heads = [] } = useGetHeadsQuery();
  const { data: locations = [] } = useGetLocationsQuery();

  const [formData, setFormData] = useState<Partial<Dragon>>({});
  const [useExistingCoordinates, setUseExistingCoordinates] = useState(true);
  const [useExistingCave, setUseExistingCave] = useState(true);
  const [useExistingKiller, setUseExistingKiller] = useState(true);
  const [useExistingHead, setUseExistingHead] = useState(true);
  const [useExistingPersonLocation, setUseExistingPersonLocation] =
    useState(true);

  const [newCoordinates, setNewCoordinates] = useState<{
    x: number;
    y: number;
  }>({ x: 0, y: 0 });
  const [newCave, setNewCave] = useState<{
    depth: number;
    numberOfTreasures: number;
  }>({ depth: 0, numberOfTreasures: 0 });
  const [newPerson, setNewPerson] = useState<{
    name: string;
    eyeColor: Color;
    hairColor: Color | null;
    weight: number;
    passportID: string;
    height?: number | null;
    location?: { name: string; x: number; y: number; id?: number } | null;
  }>({
    name: '',
    eyeColor: 'GREEN',
    hairColor: null,
    weight: 0,
    passportID: '',
    location: null,
  });
  const [newHead, setNewHead] = useState<{ toothCount: number }>({
    toothCount: 0,
  });
  const [newPersonLocation, setNewPersonLocation] = useState<{
    name: string;
    x: number;
    y: number;
  }>({ name: '', x: 0, y: 0 });

  useEffect(() => {
    if (dragonToUpdate && isOpen) {
      setFormData({
        id: dragonToUpdate.id,
        name: dragonToUpdate.name,
        age: dragonToUpdate.age,
        color: dragonToUpdate.color,
        type: dragonToUpdate.type,
        character: dragonToUpdate.character,
        coordinates: dragonToUpdate.coordinates,
        cave: dragonToUpdate.cave,
        killer: dragonToUpdate.killer,
        head: dragonToUpdate.head,
        creationDate: dragonToUpdate.creationDate,
      });
      setUseExistingCoordinates(true);
      setUseExistingCave(true);
      setUseExistingKiller(dragonToUpdate.killer !== null);
      setUseExistingHead(dragonToUpdate.head !== undefined);
      setUseExistingPersonLocation(true);
      setNewCoordinates({
        x: dragonToUpdate.coordinates.x,
        y: dragonToUpdate.coordinates.y,
      });
      setNewCave({
        depth: dragonToUpdate.cave.depth,
        numberOfTreasures: dragonToUpdate.cave.numberOfTreasures,
      });
      if (dragonToUpdate.killer) {
        setNewPerson({
          name: dragonToUpdate.killer.name,
          eyeColor: dragonToUpdate.killer.eyeColor,
          hairColor: dragonToUpdate.killer.hairColor ?? null,
          weight: dragonToUpdate.killer.weight,
          passportID: dragonToUpdate.killer.passportID,
          height: dragonToUpdate.killer.height ?? null,
          location: dragonToUpdate.killer.location
            ? {
                id: dragonToUpdate.killer.location.id,
                name: dragonToUpdate.killer.location.name || '',
                x: dragonToUpdate.killer.location.x,
                y: dragonToUpdate.killer.location.y,
              }
            : null,
        });
      }
      if (dragonToUpdate.head) {
        setNewHead({ toothCount: dragonToUpdate.head.toothCount });
      }
    }
  }, [dragonToUpdate, isOpen]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!dragonToUpdate) return;

    const getCoordinates = () => {
      if (
        useExistingCoordinates &&
        typeof formData.coordinates === 'object' &&
        formData.coordinates?.id
      ) {
        return formData.coordinates;
      }
      const coords =
        useExistingCoordinates && typeof formData.coordinates === 'object'
          ? formData.coordinates
          : { x: newCoordinates.x, y: newCoordinates.y };
      return {
        id: (formData.coordinates as Coordinates)?.id,
        x: coords.x ?? 0,
        y: coords.y ?? 0,
      };
    };

    const getCave = () => {
      if (
        useExistingCave &&
        typeof formData.cave === 'object' &&
        formData.cave?.id
      ) {
        return formData.cave;
      }
      const cave =
        useExistingCave && typeof formData.cave === 'object'
          ? formData.cave
          : {
              depth: newCave.depth,
              numberOfTreasures: newCave.numberOfTreasures,
            };
      return {
        id: (formData.cave as DragonCave)?.id,
        depth: cave.depth ?? 0,
        numberOfTreasures: cave.numberOfTreasures ?? 0,
      };
    };

    const getKiller = () => {
      if (
        useExistingKiller &&
        formData.killer &&
        typeof formData.killer === 'object' &&
        formData.killer.id
      ) {
        return formData.killer;
      }
      if (useExistingKiller) {
        return null;
      }
      const person =
        typeof formData.killer === 'object' && formData.killer
          ? formData.killer
          : {
              name: newPerson.name,
              eyeColor: newPerson.eyeColor,
              hairColor: newPerson.hairColor,
              weight: newPerson.weight,
              passportID: newPerson.passportID,
              height: newPerson.height,
            };
      const location =
        useExistingPersonLocation && newPerson.location?.id
          ? newPerson.location
          : useExistingPersonLocation
            ? null
            : {
                id: undefined,
                name: newPersonLocation.name,
                x: newPersonLocation.x,
                y: newPersonLocation.y,
              };
      return {
        ...person,
        location,
      };
    };

    const getHead = () => {
      if (
        useExistingHead &&
        formData.head &&
        typeof formData.head === 'object' &&
        formData.head.id
      ) {
        return formData.head;
      }
      if (useExistingHead) {
        return undefined;
      }
      const head =
        typeof formData.head === 'object' && formData.head
          ? formData.head
          : { toothCount: newHead.toothCount };
      return {
        id: undefined,
        toothCount: head.toothCount ?? 0,
      };
    };

    const payload: Dragon = {
      id: dragonToUpdate.id,
      name: formData.name!,
      age: formData.age!,
      color: formData.color!,
      type: formData.type!,
      character: formData.character ?? null,
      creationDate: dragonToUpdate.creationDate,
      coordinates: getCoordinates(),
      cave: getCave(),
      killer: getKiller(),
      head: getHead(),
    };

    try {
      await updateDragon({ dragon: payload }).unwrap();
      toast.success('Dragon updated successfully!');
      onClose();
    } catch (e: any) {
      const errorMessage =
        e?.data?.message || e?.message || 'Failed to update dragon';
      toast.error(errorMessage);
    }
  };

  const handleChange = (field: keyof Dragon, value: any) => {
    setFormData((prev) => ({ ...prev, [field]: value }));
  };

  if (!isOpen || !dragonToUpdate) return null;

  return (
    <div className="fixed inset-0 bg-black/50 flex justify-center items-center z-50 overflow-y-auto">
      <div className="bg-white p-6 rounded-lg shadow-xl max-w-4xl w-full my-8 max-h-[90vh] overflow-y-auto">
        <h2 className="text-2xl font-bold mb-6">Update Dragon</h2>

        <form onSubmit={handleSubmit} className="space-y-6">
          <FormSection title="Basic Information" columns={2}>
            <FormInputString
              label="Name"
              value={formData.name || ''}
              onChange={(val) => handleChange('name', val)}
              validation={DragonValidation.name}
            />
            <FormInputInt
              label="Age"
              value={formData.age || ''}
              onChange={(val) => handleChange('age', val)}
              validation={DragonValidation.age}
            />
          </FormSection>

          <FormSection title="Dragon Characteristics" columns={3}>
            <FormSelect
              label="Color"
              value={formData.color || 'GREEN'}
              onChange={(val) => handleChange('color', val as Color)}
              options={[
                { value: 'GREEN', label: 'Green' },
                { value: 'RED', label: 'Red' },
                { value: 'WHITE', label: 'White' },
                { value: 'BROWN', label: 'Brown' },
              ]}
              required
            />
            <FormSelect
              label="Type"
              value={formData.type || 'WATER'}
              onChange={(val) => handleChange('type', val as DragonType)}
              options={[
                { value: 'WATER', label: 'Water' },
                { value: 'UNDERGROUND', label: 'Underground' },
                { value: 'AIR', label: 'Air' },
                { value: 'FIRE', label: 'Fire' },
              ]}
              required
            />
            <FormSelect
              label="Character"
              value={formData.character || ''}
              onChange={(val) =>
                handleChange('character', val ? (val as DragonCharacter) : null)
              }
              options={[
                { value: '', label: 'None' },
                { value: 'CUNNING', label: 'Cunning' },
                { value: 'WISE', label: 'Wise' },
                { value: 'GOOD', label: 'Good' },
                { value: 'CHAOTIC_EVIL', label: 'Chaotic Evil' },
                { value: 'FICKLE', label: 'Fickle' },
              ]}
            />
          </FormSection>

          <FormSection title="Location" columns={2}>
            <NestedEntitySelector
              label="Coordinates *"
              isCreatingNew={!useExistingCoordinates}
              onToggle={() => {
                setUseExistingCoordinates(!useExistingCoordinates);
              }}
              existingEntitySelect={
                <select
                  required
                  className="w-full border rounded px-3 py-2"
                  value={
                    typeof formData.coordinates === 'object' &&
                    formData.coordinates?.id
                      ? formData.coordinates.id
                      : ''
                  }
                  onChange={(e) => {
                    const coord = coordinates.find(
                      (c) => c.id === parseInt(e.target.value),
                    );
                    handleChange('coordinates', coord || formData.coordinates);
                  }}
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
                    value={newCoordinates.x}
                    onChange={(val) =>
                      setNewCoordinates({ ...newCoordinates, x: val })
                    }
                    validation={CoordinatesValidation.x}
                  />
                  <FormInputInt
                    label="Y"
                    placeholder="Y"
                    value={newCoordinates.y}
                    onChange={(val) =>
                      setNewCoordinates({ ...newCoordinates, y: val })
                    }
                    validation={CoordinatesValidation.y}
                  />
                </div>
              }
            />

            <NestedEntitySelector
              label="Cave *"
              isCreatingNew={!useExistingCave}
              onToggle={() => {
                setUseExistingCave(!useExistingCave);
              }}
              existingEntitySelect={
                <select
                  required
                  className="w-full border rounded px-3 py-2"
                  value={
                    typeof formData.cave === 'object' && formData.cave?.id
                      ? formData.cave.id
                      : ''
                  }
                  onChange={(e) => {
                    const cave = caves.find(
                      (c) => c.id === parseInt(e.target.value),
                    );
                    handleChange('cave', cave || formData.cave);
                  }}
                >
                  <option value="">Select cave...</option>
                  {caves.map((cave) => (
                    <option key={cave.id} value={cave.id}>
                      ID {cave.id}: Depth {cave.depth}, Treasures{' '}
                      {cave.numberOfTreasures}
                    </option>
                  ))}
                </select>
              }
              newEntityForm={
                <div className="space-y-2">
                  <FormInputFloat
                    label="Depth"
                    placeholder="Depth"
                    value={newCave.depth}
                    onChange={(val) => setNewCave({ ...newCave, depth: val })}
                    validation={DragonCaveValidation.depth}
                  />
                  <FormInputInt
                    label="Number of Treasures"
                    placeholder="Number of Treasures"
                    value={newCave.numberOfTreasures}
                    onChange={(val) =>
                      setNewCave({ ...newCave, numberOfTreasures: val })
                    }
                    validation={DragonCaveValidation.numberOfTreasures}
                  />
                </div>
              }
            />
          </FormSection>

          <FormSection title="Related Entities (Optional)" columns={2}>
            <NestedEntitySelector
              label="Killer"
              isCreatingNew={!useExistingKiller}
              onToggle={() => {
                setUseExistingKiller(!useExistingKiller);
                if (useExistingKiller) {
                  handleChange('killer', null);
                }
              }}
              fullWidthWhenCreating
              existingEntitySelect={
                <select
                  className="w-full border rounded px-3 py-2"
                  value={
                    typeof formData.killer === 'object' && formData.killer?.id
                      ? formData.killer.id
                      : ''
                  }
                  onChange={(e) => {
                    if (e.target.value) {
                      const person = persons.find(
                        (p) => p.id === parseInt(e.target.value),
                      );
                      handleChange('killer', person || null);
                    } else {
                      handleChange('killer', null);
                    }
                  }}
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
                    value={newPerson.name}
                    onChange={(val) =>
                      setNewPerson({ ...newPerson, name: val })
                    }
                    validation={PersonValidation.name}
                  />
                  <FormInputString
                    label="Passport ID"
                    placeholder="Passport ID *"
                    value={newPerson.passportID}
                    onChange={(val) =>
                      setNewPerson({ ...newPerson, passportID: val })
                    }
                    validation={PersonValidation.passportID}
                  />
                  <FormSelect
                    label="Eye Color"
                    value={newPerson.eyeColor}
                    onChange={(val) =>
                      setNewPerson({ ...newPerson, eyeColor: val as Color })
                    }
                    options={[
                      { value: 'GREEN', label: 'Eye Color: Green' },
                      { value: 'RED', label: 'Eye Color: Red' },
                      { value: 'WHITE', label: 'Eye Color: White' },
                      { value: 'BROWN', label: 'Eye Color: Brown' },
                    ]}
                  />
                  <FormSelect
                    label="Hair Color"
                    value={newPerson.hairColor || ''}
                    onChange={(val) =>
                      setNewPerson({
                        ...newPerson,
                        hairColor: val ? (val as Color) : null,
                      })
                    }
                    options={[
                      { value: '', label: 'Hair Color: None' },
                      { value: 'GREEN', label: 'Hair Color: Green' },
                      { value: 'RED', label: 'Hair Color: Red' },
                      { value: 'WHITE', label: 'Hair Color: White' },
                      { value: 'BROWN', label: 'Hair Color: Brown' },
                    ]}
                  />
                  <FormInputFloat
                    label="Weight"
                    placeholder="Weight *"
                    value={newPerson.weight}
                    onChange={(val) =>
                      setNewPerson({ ...newPerson, weight: val })
                    }
                    validation={PersonValidation.weight}
                  />
                  <FormInputFloat
                    label="Height"
                    placeholder="Height"
                    value={newPerson.height || ''}
                    onChange={(val) =>
                      setNewPerson({ ...newPerson, height: val || null })
                    }
                    validation={PersonValidation.height}
                  />
                  <div className="col-span-2">
                    <NestedEntitySelector
                      label="Location"
                      isCreatingNew={!useExistingPersonLocation}
                      onToggle={() => {
                        setUseExistingPersonLocation(
                          !useExistingPersonLocation,
                        );
                      }}
                      existingEntitySelect={
                        <select
                          className="w-full border rounded px-3 py-2"
                          value={
                            newPerson.location?.id ? newPerson.location.id : ''
                          }
                          onChange={(e) => {
                            if (e.target.value) {
                              const loc = locations.find(
                                (l) => l.id === parseInt(e.target.value),
                              );
                              setNewPerson({
                                ...newPerson,
                                location: loc
                                  ? {
                                      id: loc.id,
                                      name: loc.name || '',
                                      x: loc.x,
                                      y: loc.y,
                                    }
                                  : null,
                              });
                            } else {
                              setNewPerson({ ...newPerson, location: null });
                            }
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
                            value={newPersonLocation.name}
                            onChange={(val) =>
                              setNewPersonLocation({
                                ...newPersonLocation,
                                name: val,
                              })
                            }
                            validation={LocationValidation.name}
                          />
                          <div className="grid grid-cols-2 gap-2">
                            <FormInputFloat
                              label="Location X"
                              placeholder="X"
                              value={newPersonLocation.x}
                              onChange={(val) =>
                                setNewPersonLocation({
                                  ...newPersonLocation,
                                  x: val,
                                })
                              }
                              validation={LocationValidation.x}
                            />
                            <FormInputFloat
                              label="Location Y"
                              placeholder="Y"
                              value={newPersonLocation.y}
                              onChange={(val) =>
                                setNewPersonLocation({
                                  ...newPersonLocation,
                                  y: val,
                                })
                              }
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
              isCreatingNew={!useExistingHead}
              onToggle={() => {
                setUseExistingHead(!useExistingHead);
                if (useExistingHead) {
                  handleChange('head', undefined);
                }
              }}
              existingEntitySelect={
                <select
                  className="w-full border rounded px-3 py-2"
                  value={
                    typeof formData.head === 'object' && formData.head?.id
                      ? formData.head.id
                      : ''
                  }
                  onChange={(e) => {
                    if (e.target.value) {
                      const head = heads.find(
                        (h) => h.id === parseInt(e.target.value),
                      );
                      handleChange('head', head || undefined);
                    } else {
                      handleChange('head', undefined);
                    }
                  }}
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
                  value={newHead.toothCount}
                  onChange={(val) =>
                    setNewHead({ ...newHead, toothCount: val })
                  }
                  validation={DragonHeadValidation.toothCount}
                />
              }
            />
          </FormSection>

          <div className="pt-2 flex justify-end gap-3">
            <button
              type="button"
              onClick={onClose}
              disabled={isLoading}
              className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300 disabled:bg-gray-400"
            >
              Cancel
            </button>
            <button
              type="submit"
              disabled={isLoading}
              className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 disabled:bg-gray-400"
            >
              {isLoading ? 'Updating...' : 'Update Dragon'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
