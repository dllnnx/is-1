import React from "react";
import type {ValidationRule} from "../utils/validation";
import {validateValue} from "../utils/validation";

interface FormInputIntProps {
    label: string;
    value: number | string;
    onChange: (value: number) => void;
    validation?: ValidationRule;
    placeholder?: string;
}

export const FormInputInt = ({
                                 label,
                                 value,
                                 onChange,
                                 validation,
                                 placeholder,
                             }: FormInputIntProps) => {
    const min = validation?.min ?? Number.MIN_SAFE_INTEGER;
    const max = validation?.max ?? Number.MAX_SAFE_INTEGER;
    const required = validation?.required ?? false;
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const inputValue = e.target.value;

        if (inputValue === '') {
            onChange(0);
            return;
        }

        const intValue = parseInt(inputValue, 10);

        if (!isNaN(intValue)) {
            const clampedValue = Math.max(min, Math.min(max, intValue));
            onChange(clampedValue);
        }
    };

    const numValue = typeof value === 'string' ? parseInt(value, 10) : value;
    const validationResult = validateValue(numValue, validation ?? {});
    const isValid = validationResult.valid && !isNaN(numValue);

    return (
        <div>
            <label className="block text-sm font-medium mb-1">
                {label} {required && <span className="text-red-500">*</span>}
            </label>
            <input
                type="number"
                required={required}
                placeholder={placeholder}
                step="1"
                min={min}
                max={max}
                className={`w-full border rounded px-3 py-2 ${
                    !isValid ? 'border-red-500' : 'border-gray-300'
                } focus:outline-none focus:ring-2 focus:ring-blue-500`}
                value={value}
                onChange={handleChange}
                onKeyDown={(e) => {
                    if (e.key === '.' || e.key === ',') {
                        e.preventDefault();
                    }
                }}
            />
            <div className="mt-1">
                {!isValid && validationResult.error && (
                    <span className="text-xs text-red-500">
                        {validationResult.error}
                    </span>
                )}
                {min !== Number.MIN_SAFE_INTEGER || max !== Number.MAX_SAFE_INTEGER ? (
                    <span className="text-xs text-gray-500">
                        Range: {min.toLocaleString()} to {max.toLocaleString()}
                    </span>
                ) : null}
            </div>
        </div>
    );
};
