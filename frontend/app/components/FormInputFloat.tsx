import React from "react";
import type {ValidationRule} from "../utils/validation";
import {validateValue} from "../utils/validation";

interface FormInputFloatProps {
    label: string;
    value: number | string;
    onChange: (value: number) => void;
    validation?: ValidationRule;
    placeholder?: string;
    step?: number;
}

export const FormInputFloat = ({
                                   label,
                                   value,
                                   onChange,
                                   validation,
                                   placeholder,
                                   step = 0.01
                               }: FormInputFloatProps) => {
    const min = validation?.min ?? -Number.MAX_VALUE;
    const max = validation?.max ?? Number.MAX_VALUE;
    const required = validation?.required ?? false;
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const inputValue = e.target.value;

        if (inputValue === '') {
            onChange(0);
            return;
        }

        const floatValue = parseFloat(inputValue);

        if (!isNaN(floatValue)) {
            onChange(floatValue);
        }
    };

    const numValue = typeof value === 'string' ? parseFloat(value) : value;
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
                step={step}
                min={min}
                max={max}
                className={`w-full border rounded px-3 py-2 ${
                    !isValid ? 'border-red-500' : 'border-gray-300'
                } focus:outline-none focus:ring-2 focus:ring-blue-500`}
                value={value}
                onChange={handleChange}
            />
            <div className="mt-1">
                {!isValid && validationResult.error && (
                    <span className="text-xs text-red-500">
                        {validationResult.error}
                    </span>
                )}
                {min !== -Number.MAX_VALUE || max !== Number.MAX_VALUE ? (
                    <span className="text-xs text-gray-500">
                        Range: {min.toLocaleString()} to {max.toLocaleString()} (up to decimal places)
                    </span>
                ) : null}
            </div>
        </div>
    );
};
