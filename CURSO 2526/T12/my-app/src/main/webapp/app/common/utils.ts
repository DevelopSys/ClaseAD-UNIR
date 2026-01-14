import { NavigateFunction } from 'react-router';
import { useTranslation } from 'react-i18next';
import { UseFormSetError } from 'react-hook-form';
import { TFunction } from 'i18next';
import axios from 'axios';
import * as yup from 'yup';


/**
 * Handle server errors and show the error page if required. If provided, write all field errors
 * from the server response to the matching form fields.
 */
export function handleServerError(error: any, navigate: NavigateFunction, setError?: UseFormSetError<any>,
    t?: TFunction, getMessage?: (key: string) => string|undefined) {
  // show general error page
  if (!axios.isAxiosError<ErrorResponse>(error) || !error?.response?.data?.fieldErrors || !setError || !t) {
    navigate('/error', {
          state: {
            errorStatus: error?.response?.data?.status || '503'
           }
        });
    return;
  }
  // collect errors for each field
  const errorResponse = error.response.data as ErrorResponse;
  const errorsMap: Record<string, Record<string, string>> = {};
  for (const fieldError of errorResponse.fieldErrors!) {
    const fieldName = fieldError.property.split(/[\.\[]/)[0]!;
    if (!errorsMap[fieldName]) {
      errorsMap[fieldName] = {};
    }
    // look for message under key <fieldName>.<code> or <code>
    // use global error message or error code as fallback
    let errorMessage = t(fieldError.code) || fieldError.code;
    if (getMessage) {
      errorMessage = getMessage(fieldError.property + '.' + fieldError.code) ||
          getMessage(fieldError.code) || errorMessage;
    }
    // json nested errors
    if (fieldName !== fieldError.property) {
      errorMessage = fieldError.property + ': ' + errorMessage;
    }
    errorsMap[fieldName][fieldError.code] = errorMessage;
  }
  // write errors to fields
  for (const [key, value] of Object.entries(errorsMap)) {
    for (const [type, message] of Object.entries(value)) {
      setError(key, { type: type, message: message })
    }
  }
}

function emptyToNull(val:any, inputVal:any) {
  // handle input in number field: keep NaN for wrong input
  if (val !== val) {
    return !inputVal || (typeof inputVal === 'string' && !inputVal.trim()) ? null : NaN;
  }
  // trim and return empty input as null
  return (val && typeof val === 'string' ? val.trim() : val) || null;
}

/**
 * Extends the yup namespace with the "emptyToNull" method and define default validation error messages.
 */
export function setYupDefaults() {
  const { t } = useTranslation();
  yup.addMethod(yup.StringSchema, 'emptyToNull', function () {
    return this.transform(emptyToNull).nullable();
  });
  yup.addMethod(yup.NumberSchema, 'emptyToNull', function () {
    return this.transform(emptyToNull).nullable();
  });
  yup.setLocale({
    mixed: {
      required: t('required')    },
    string: {
      max: t('maxlength')
    }
  });
}

interface FieldError {

  code: string;
  property: string;
  message: string;
  rejectedValue: any|null;
  path: string|null;

}

export interface ErrorResponse {

  status: number;
  code: string;
  message: string;
  fieldErrors?: FieldError[];

}
