import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';

const baseQuery = fetchBaseQuery({
  baseUrl: 'http://89.169.150.230:8080/',
});

export const api = createApi({
  baseQuery: baseQuery,
  endpoints: () => ({}),
});
