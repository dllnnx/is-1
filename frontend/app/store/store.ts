import {configureStore} from '@reduxjs/toolkit';
import {dragonsApi} from "../gen/types.generated";

export const store = configureStore({
    reducer: {
        [dragonsApi.reducerPath]: dragonsApi.reducer,
    },
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware().concat(dragonsApi.middleware),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
