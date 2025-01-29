/**
 * List of methods supported by the library.
 */
export const METHODS = ['shafi', 'hanafi'] as const;

/**
 * Default method to use if none is provided.
 */
export const DEFAULT_METHOD: Method = 'shafi';

/**
 * Type for methods supported by the library.
 */
export type Method = (typeof METHODS)[number];

/**
 * Check if the given value is a valid method.
 */
export function isValidMethod(method: any): method is Method {
  return METHODS.includes(method);
}
