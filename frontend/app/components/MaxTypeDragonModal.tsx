import React from 'react';
import { useGetMaxTypeDragonQuery } from '~/gen/types.generated';

interface Props {
  isOpen: boolean;
  onClose: () => void;
}

export const MaxTypeDragonModal = ({ isOpen, onClose }: Props) => {
  const {
    data: dragon,
    isLoading,
    error,
  } = useGetMaxTypeDragonQuery(undefined, {
    skip: !isOpen,
  });

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black/50 flex justify-center items-center z-50">
      <div className="bg-white p-6 rounded-lg shadow-xl max-w-2xl w-full max-h-[90vh] overflow-y-auto">
        <h2 className="text-xl font-bold mb-4">Dragon with Maximum Type</h2>

        {isLoading && <div className="text-gray-600">Loading...</div>}

        {error && (
          <div className="text-red-600">
            Error loading dragon. Please try again.
          </div>
        )}

        {!isLoading && !error && dragon && (
          <div className="space-y-4">
            <div className="text-lg font-semibold mb-4">
              Dragon with the max type:
            </div>
            <div className="bg-gray-50 rounded-lg p-4 space-y-3">
              <div className="grid grid-cols-2 gap-3">
                <div>
                  <span className="text-sm font-semibold text-gray-600">
                    ID:
                  </span>
                  <span className="ml-2 text-gray-800">{dragon.id}</span>
                </div>
                <div>
                  <span className="text-sm font-semibold text-gray-600">
                    Name:
                  </span>
                  <span className="ml-2 text-gray-800">{dragon.name}</span>
                </div>
                <div>
                  <span className="text-sm font-semibold text-gray-600">
                    Age:
                  </span>
                  <span className="ml-2 text-gray-800">{dragon.age}</span>
                </div>
                <div>
                  <span className="text-sm font-semibold text-gray-600">
                    Color:
                  </span>
                  <span className="ml-2 text-gray-800">{dragon.color}</span>
                </div>
                <div>
                  <span className="text-sm font-semibold text-gray-600">
                    Type:
                  </span>
                  <span className="ml-2 text-gray-800 font-semibold">
                    {dragon.type}
                  </span>
                </div>
                {dragon.character && (
                  <div>
                    <span className="text-sm font-semibold text-gray-600">
                      Character:
                    </span>
                    <span className="ml-2 text-gray-800">
                      {dragon.character}
                    </span>
                  </div>
                )}
              </div>

              {dragon.coordinates && (
                <div className="border-t pt-3">
                  <span className="text-sm font-semibold text-gray-600">
                    Coordinates:
                  </span>
                  <span className="ml-2 text-gray-800">
                    ({dragon.coordinates.x}, {dragon.coordinates.y})
                  </span>
                </div>
              )}

              {dragon.cave && (
                <div className="border-t pt-3">
                  <span className="text-sm font-semibold text-gray-600">
                    Cave:
                  </span>
                  <span className="ml-2 text-gray-800">
                    Depth {dragon.cave.depth}, {dragon.cave.numberOfTreasures}{' '}
                    treasures
                  </span>
                </div>
              )}

              {dragon.killer && (
                <div className="border-t pt-3">
                  <span className="text-sm font-semibold text-gray-600">
                    Killer:
                  </span>
                  <span className="ml-2 text-gray-800">
                    {dragon.killer.name}
                  </span>
                </div>
              )}

              {dragon.head && (
                <div className="border-t pt-3">
                  <span className="text-sm font-semibold text-gray-600">
                    Head:
                  </span>
                  <span className="ml-2 text-gray-800">
                    {dragon.head.toothCount} teeth
                  </span>
                </div>
              )}

              {dragon.creationDate && (
                <div className="border-t pt-3">
                  <span className="text-sm font-semibold text-gray-600">
                    Created:
                  </span>
                  <span className="ml-2 text-gray-800">
                    {new Date(dragon.creationDate).toLocaleDateString()}
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
