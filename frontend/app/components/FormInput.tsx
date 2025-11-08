interface FormInputProps {
    label: string;
    type?: "text" | "number";
    value: string | number;
    onChange: (value: string | number) => void;
    required?: boolean;
    placeholder?: string;
    min?: number;
}

export const FormInput = ({
                              label,
                              type = "text",
                              value,
                              onChange,
                              required = false,
                              placeholder,
                              min
                          }: FormInputProps) => {
    return (
        <div>
            <label className="block text-sm font-medium mb-1">
                {label} {required && "*"}
            </label>
            <input
                type={type}
                required={required}
                min={min}
                placeholder={placeholder}
                step={type === "number" ? "0.0001" : undefined}
                className="w-full border rounded px-3 py-2"
                value={value}
                onKeyDown={(e) => {
                    if (type === "number") {
                        const input = e.currentTarget as HTMLInputElement;
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
                    }
                }}
                onPaste={(e) => {
                    if (type === "number") {
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
                    }
                }}
                onInput={(e) => {
                    if (type === "number") {
                        const input = e.currentTarget as HTMLInputElement;
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
                    }
                }}
                onChange={(e) => {
                    if (type === "number") {
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
                    } else {
                        onChange(e.target.value);
                    }
                }}
            />
        </div>
    );
};
