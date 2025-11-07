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
    const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
        const input = e.currentTarget;
        const currentValue = input.value;
        const cursorStart = input.selectionStart || 0;
        const cursorEnd = input.selectionEnd || 0;
        const dotIndex = currentValue.indexOf('.');
        
        if (e.key === '.' || e.key === ',') {
            if (dotIndex !== -1) {
                e.preventDefault();
                return;
            }
        }
        
        if (e.key.length === 1 && /[0-9]/.test(e.key)) {
            if (dotIndex !== -1 && cursorStart > dotIndex) {
                const textBeforeCursor = currentValue.substring(0, cursorStart);
                const textAfterCursor = currentValue.substring(cursorEnd);
                const newValue = textBeforeCursor + e.key + textAfterCursor;
                const newDotIndex = newValue.indexOf('.');
                
                if (newDotIndex !== -1) {
                    const decimalPart = newValue.substring(newDotIndex + 1);
                    if (decimalPart.length > 4) {
                        e.preventDefault();
                        return;
                    }
                }
            }
        }
    };

    const handlePaste = (e: React.ClipboardEvent<HTMLInputElement>) => {
        e.preventDefault();
        const pastedText = e.clipboardData.getData('text');
        const cleaned = pastedText.replace(/[^0-9.-]/g, '');
        const parts = cleaned.split('.');
        let validValue = parts[0] || '';
        if (parts.length > 1) {
            validValue += '.' + parts[1].substring(0, 4);
        }
        const floatValue = parseFloat(validValue);
        if (!isNaN(floatValue)) {
            onChange(floatValue);
        }
    };

    const handleInput = (e: React.FormEvent<HTMLInputElement>) => {
        const input = e.currentTarget;
        let inputValue = input.value;

        if (inputValue === '') {
            onChange(0);
            return;
        }

        const parts = inputValue.split('.');
        if (parts.length === 2 && parts[1].length > 4) {
            const truncated = parts[0] + '.' + parts[1].substring(0, 4);
            const floatValue = parseFloat(truncated);
            if (!isNaN(floatValue)) {
                onChange(floatValue);
                setTimeout(() => {
                    const dotIndex = truncated.indexOf('.');
                    if (dotIndex !== -1) {
                        input.setSelectionRange(truncated.length, truncated.length);
                    }
                }, 0);
            }
            return;
        }

        const floatValue = parseFloat(inputValue);
        if (!isNaN(floatValue)) {
            onChange(floatValue);
        }
    };

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const inputValue = e.target.value;

        if (inputValue === '') {
            onChange(0);
            return;
        }

        const parts = inputValue.split('.');
        if (parts.length === 2 && parts[1].length > 4) {
            const truncated = parts[0] + '.' + parts[1].substring(0, 4);
            const floatValue = parseFloat(truncated);
            if (!isNaN(floatValue)) {
                onChange(floatValue);
            }
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
                onKeyDown={handleKeyDown}
                onPaste={handlePaste}
                onInput={handleInput}
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
