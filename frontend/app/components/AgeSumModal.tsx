import React from 'react';
import {useGetDragonsAgeSumQuery} from '~/gen/types.generated';

interface Props {
    isOpen: boolean;
    onClose: () => void;
}

export const AgeSumModal = ({isOpen, onClose}: Props) => {
    const {data: ageSum, isLoading, error} = useGetDragonsAgeSumQuery(undefined, {
        skip: !isOpen,
    });

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-black/50 flex justify-center items-center z-50">
            <div className="bg-white p-6 rounded-lg shadow-xl max-w-md w-full">
                <h2 className="text-xl font-bold mb-4">Dragon Ages Sum</h2>
                
                {isLoading && (
                    <div className="text-gray-600">Loading...</div>
                )}
                
                {error && (
                    <div className="text-red-600">
                        Error loading age sum. Please try again.
                    </div>
                )}
                
                {!isLoading && !error && ageSum !== undefined && (
                    <div className="text-lg">
                        <span className="font-semibold">Sum of all dragon ages: </span>
                        <span>{ageSum}</span>
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

