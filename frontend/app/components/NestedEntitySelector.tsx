import {ReactNode} from "react";

interface EntitySelectorProps {
    label: string;
    isCreatingNew: boolean;
    onToggle: () => void;
    existingEntitySelect: ReactNode;
    newEntityForm: ReactNode;
    fullWidthWhenCreating?: boolean;
}

export const NestedEntitySelector = ({
                                         label,
                                         isCreatingNew,
                                         onToggle,
                                         existingEntitySelect,
                                         newEntityForm,
                                         fullWidthWhenCreating = false,
                                     }: EntitySelectorProps) => {
    return (
        <div className={`border rounded p-3 ${fullWidthWhenCreating && isCreatingNew ? 'col-span-2' : ''}`}>
            <div className="flex justify-between items-center mb-2">
                <label className="block text-sm font-medium">{label}</label>
                <button
                    type="button"
                    onClick={onToggle}
                    className="text-xs text-blue-600 hover:underline"
                >
                    {isCreatingNew ? "Select Existing" : "Create New"}
                </button>
            </div>

            {isCreatingNew ? newEntityForm : existingEntitySelect}
        </div>
    );
};
