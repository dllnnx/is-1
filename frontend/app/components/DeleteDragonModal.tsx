import React, { useState } from 'react';
import type { Dragon } from '~/gen/types.generated';
import { useGetDragonsQuery } from '~/gen/types.generated';

interface Props {
  isOpen: boolean;
  onClose: () => void;
  onConfirm: (newOwnerId: number) => void;
  dragonToDelete: Dragon | null;
  isLoading: boolean;
}

export const DeleteDragonModal = ({
  isOpen,
  onClose,
  onConfirm,
  dragonToDelete,
  isLoading,
}: Props) => {
  const [newOwnerId, setNewOwnerId] = useState<number | undefined>(undefined);

  const { data: allDragonsData } = useGetDragonsQuery(
    { body: { pagination: { size: 1000 } } },
    {
      skip: !isOpen,
    },
  );

  if (!isOpen || !dragonToDelete) return null;

  const potentialNewOwners =
    allDragonsData?.dragons?.filter((d) => d.id !== dragonToDelete.id) || [];

  const handleConfirm = () => {
    if (newOwnerId) {
      onConfirm(newOwnerId);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex justify-center items-center z-50">
      <div className="bg-white p-6 rounded-lg shadow-xl max-w-md w-full">
        <h2 className="text-xl font-bold mb-4">Delete Dragon and Reassign</h2>
        <p>
          You are about to delete the dragon{' '}
          <span className="font-semibold">{dragonToDelete.name}</span>.
        </p>
        <p className="mt-2 text-sm">
          All its dependencies (Killer, Cave, etc.) must be reassigned to
          another dragon.
        </p>

        <div className="mt-4">
          <label className="block font-semibold mb-1">Reassign to:</label>
          <select
            className="w-full mt-1 border rounded px-3 py-2"
            value={newOwnerId || ''}
            onChange={(e) =>
              setNewOwnerId(
                e.target.value ? parseInt(e.target.value) : undefined,
              )
            }
            required
          >
            <option value="">Select a new owner dragon...</option>
            {potentialNewOwners.map((d) => (
              <option key={d.id} value={d.id}>
                {d.name} (ID: {d.id})
              </option>
            ))}
          </select>
        </div>

        <div className="mt-6 flex justify-end gap-3">
          <button
            onClick={onClose}
            disabled={isLoading}
            className="px-4 py-2 bg-gray-200 rounded hover:bg-gray-300"
          >
            Cancel
          </button>
          <button
            onClick={handleConfirm}
            disabled={isLoading || !newOwnerId}
            className="px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700 disabled:bg-gray-400"
          >
            {isLoading ? 'Deleting...' : 'Delete & Reassign'}
          </button>
        </div>
      </div>
    </div>
  );
};
