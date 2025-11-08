interface FormSelectProps<T = string> {
    label: string;
    value: T;
    onChange: (value: T) => void;
    options: { value: T; label: string }[];
    required?: boolean;
}

export const FormSelect = <T extends string | number>({
                                                          label,
                                                          value,
                                                          onChange,
                                                          options,
                                                          required = false
                                                      }: FormSelectProps<T>) => {
    return (
        <div>
            <label className="block text-sm font-medium mb-1">
                {label} {required && "*"}
            </label>
            <select
                required={required}
                className="w-full border rounded px-3 py-2"
                value={value as string | number}
                onChange={(e) => onChange(e.target.value as T)}
            >
                {options.map((option) => (
                    <option key={String(option.value)} value={option.value}>
                        {option.label}
                    </option>
                ))}
            </select>
        </div>
    );
};
