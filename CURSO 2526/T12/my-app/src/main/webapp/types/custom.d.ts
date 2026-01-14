import * as yup from 'yup';

declare module 'yup' {
  interface StringSchema<TType extends yup.Maybe<string> = string|undefined, TContext = yup.AnyObject, TDefault = undefined, TFlags extends yup.Flags = ''> extends yup.Schema<TType, TContext, TDefault, TFlags> {
    emptyToNull(msg?: yup.Message): yup.StringSchema<TType|null, TContext, TDefault, TFlags>;
  }
  interface NumberSchema<TType extends yup.Maybe<number> = number|undefined, TContext = yup.AnyObject, TDefault = undefined, TFlags extends yup.Flags = ''> extends yup.Schema<TType, TContext, TDefault, TFlags> {
    emptyToNull(msg?: yup.Message): yup.NumberSchema<TType|null, TContext, TDefault, TFlags>;
  }
}

declare module '*.woff2' {
  const content: string;
  export default content;
}

declare module '*.woff' {
  const content: string;
  export default content;
}

declare module '*.ttf' {
  const content: string;
  export default content;
}

declare module '*.png' {
  const content: string;
  export default content;
}

declare module '*.jpg' {
  const content: string;
  export default content;
}

declare module '*.jpeg' {
  const content: string;
  export default content;
}

declare module '*.gif' {
  const content: string;
  export default content;
}

declare module '*.svg' {
  const content: string;
  export default content;
}

declare module '*.webp' {
  const content: string;
  export default content;
}
