import React from "react";
import type {ValidationRule} from "../utils/validation";
import {validateValue} from "../utils/validation";

interface FormInputStringProps {
    label: string;
    value: string;
    onChange: (value: string) => void;
    validation?: ValidationRule;
    placeholder?: string;
}

export const FormInputString = ({
                                    label,
                                    value,
                                    onChange,
                                    validation,
                                    placeholder,
                                }: FormInputStringProps) => {
    const maxLength = validation?.maxLength ?? 255;
    const minLength = validation?.minLength ?? 0;
    const required = validation?.required ?? false;

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const newValue = e.target.value;
        if (newValue.length <= maxLength) {
            onChange(newValue);
        }
    };

    const validationResult = validateValue(value, validation ?? {});
    const isValid = validationResult.valid;
    const remainingChars = maxLength - value.length;

    return (
        <div>
            <label className="block text-sm font-medium mb-1">
                {label} {required && <span className="text-red-500">*</span>}
            </label>
            <input
                type="text"
                required={required}
                placeholder={placeholder}
                className={`w-full border rounded px-3 py-2 ${
                    !isValid ? 'border-red-500' : 'border-gray-300'
                } focus:outline-none focus:ring-2 focus:ring-blue-500`}
                value={value}
                onChange={handleChange}
                maxLength={maxLength}
            />
            <div className="flex justify-between mt-1">
                {!isValid && validationResult.error && (
                    <span className="text-xs text-red-500">
                        {validationResult.error}
                    </span>
                )}
                <span className={`text-xs ${remainingChars < 20 ? 'text-orange-500' : 'text-gray-500'} ml-auto`}>
                    {remainingChars} / {maxLength} characters
                </span>
            </div>
        </div>
    );
};
