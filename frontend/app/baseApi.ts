import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';
import type { UserRole, ImportDragonResponse } from './gen/types.generated';

const baseQuery = fetchBaseQuery({
  baseUrl: 'http://localhost:8080/',
});

export const api = createApi({
  baseQuery: baseQuery,
  endpoints: () => ({}),
});

type ImportDragonsWithFileArg = {
  role: UserRole;
  file: File;
};

const enhancedApi = api.injectEndpoints({
  endpoints: (build) => ({
    importDragonsWithFile: build.mutation<ImportDragonResponse, ImportDragonsWithFileArg>({
      query: ({ role, file }) => {
        const formData = new FormData();
        formData.append('file', file);
        return {
          url: '/dragons/import',
          method: 'POST',
          body: formData,
          params: { role },
        };
      },
    }),
  }),
  overrideExisting: false,
});

export const { useImportDragonsWithFileMutation } = enhancedApi;
