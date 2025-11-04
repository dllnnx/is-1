export interface ValidationRule {
    min?: number;
    max?: number;
    maxLength?: number;
    minLength?: number;
    required?: boolean;
    pattern?: RegExp;
    errorMessage?: string;
}

export const DragonValidation = {
    id: {
        min: 1,
        max: 2147483647,
        required: true,
        errorMessage: "Must be greater than 0"
    } as ValidationRule,

    name: {
        minLength: 1,
        maxLength: 255,
        required: true,
        errorMessage: "Must not be null or empty"
    } as ValidationRule,

    age: {
        min: 1,
        max: 2147483647,
        required: true,
        errorMessage: "Must be greater than 0"
    } as ValidationRule,
} as const;

export const CoordinatesValidation = {
    x: {
        min: -999999999,
        max: 999999999,
        required: true,
    } as ValidationRule,

    y: {
        min: -999999999,
        max: 999999999,
        required: true,
    } as ValidationRule,
} as const;

export const DragonCaveValidation = {
    depth: {
        min: 0,
        max: 999999999,
        required: true,
    } as ValidationRule,

    numberOfTreasures: {
        min: 0,
        max: 2147483647,
        required: true,
    } as ValidationRule,
} as const;

export const PersonValidation = {
    name: {
        minLength: 1,
        maxLength: 255,
        required: true,
        errorMessage: "Must not be null or empty"
    } as ValidationRule,

    passportID: {
        minLength: 1,
        maxLength: 50,
        required: true,
        errorMessage: "Unique passport identifier. Must not be null"
    } as ValidationRule,

    weight: {
        min: 0.01,
        max: 999999,
        required: true,
        errorMessage: "Must be greater than 0"
    } as ValidationRule,

    height: {
        min: 0.01,
        max: 999999,
        required: false,
        errorMessage: "Must be greater than 0 if present"
    } as ValidationRule,
} as const;

export const DragonHeadValidation = {
    toothCount: {
        min: 0,
        max: 2147483647,
        required: true,
    } as ValidationRule,
} as const;

export const LocationValidation = {
    x: {
        min: -999999999,
        max: 999999999,
        required: true,
    } as ValidationRule,

    y: {
        min: -999999999,
        max: 999999999,
        required: true,
    } as ValidationRule,

    name: {
        maxLength: 255,
        required: false,
    } as ValidationRule,
} as const;

export const validateValue = (value: any, rule: ValidationRule): { valid: boolean; error?: string } => {
    if (rule.required && (value === null || value === undefined || value === '')) {
        return {valid: false, error: rule.errorMessage || 'This field is required'};
    }

    if (typeof value === 'string') {
        if (rule.minLength !== undefined && value.length < rule.minLength) {
            return {valid: false, error: `Minimum length is ${rule.minLength}`};
        }
        if (rule.maxLength !== undefined && value.length > rule.maxLength) {
            return {valid: false, error: `Maximum length is ${rule.maxLength}`};
        }
        if (rule.pattern && !rule.pattern.test(value)) {
            return {valid: false, error: rule.errorMessage || 'Invalid format'};
        }
    }

    if (typeof value === 'number') {
        if (rule.min !== undefined && value < rule.min) {
            return {valid: false, error: `Minimum value is ${rule.min}`};
        }
        if (rule.max !== undefined && value > rule.max) {
            return {valid: false, error: `Maximum value is ${rule.max}`};
        }
    }

    return {valid: true};
};
