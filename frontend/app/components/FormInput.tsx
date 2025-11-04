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
                className="w-full border rounded px-3 py-2"
                value={value}
                onChange={(e) => {
                    const newValue = type === "number"
                        ? parseFloat(e.target.value)
                        : e.target.value;
                    onChange(newValue);
                }}
            />
        </div>
    );
};
