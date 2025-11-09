import { api } from '../baseApi';
const injectedRtkApi = api.injectEndpoints({
  endpoints: (build) => ({
    createDragon: build.mutation<CreateDragonApiResponse, CreateDragonApiArg>({
      query: (queryArg) => ({
        url: `/dragons`,
        method: 'POST',
        body: queryArg.dragonCreate,
      }),
    }),
    updateDragon: build.mutation<UpdateDragonApiResponse, UpdateDragonApiArg>({
      query: (queryArg) => ({
        url: `/dragons`,
        method: 'PUT',
        body: queryArg.dragon,
      }),
    }),
    getDragonById: build.query<GetDragonByIdApiResponse, GetDragonByIdApiArg>({
      query: (queryArg) => ({ url: `/dragons/${queryArg.id}` }),
    }),
    deleteDragonById: build.mutation<
      DeleteDragonByIdApiResponse,
      DeleteDragonByIdApiArg
    >({
      query: (queryArg) => ({
        url: `/dragons/${queryArg.id}`,
        method: 'DELETE',
      }),
    }),
    getDragons: build.query<GetDragonsApiResponse, GetDragonsApiArg>({
      query: (queryArg) => ({
        url: `/dragons/filter`,
        method: 'POST',
        body: queryArg.body,
      }),
    }),
    deleteDragonByAge: build.mutation<
      DeleteDragonByAgeApiResponse,
      DeleteDragonByAgeApiArg
    >({
      query: (queryArg) => ({
        url: `/dragons/ages/${queryArg.age}`,
        method: 'DELETE',
      }),
    }),
    getDragonsAgeSum: build.query<
      GetDragonsAgeSumApiResponse,
      GetDragonsAgeSumApiArg
    >({
      query: () => ({ url: `/dragons/ages/sum` }),
    }),
    getMaxTypeDragon: build.query<
      GetMaxTypeDragonApiResponse,
      GetMaxTypeDragonApiArg
    >({
      query: () => ({ url: `/dragons/types/max` }),
    }),
    deleteDragonsInCaveWithMaxDepth: build.mutation<
      DeleteDragonsInCaveWithMaxDepthApiResponse,
      DeleteDragonsInCaveWithMaxDepthApiArg
    >({
      query: () => ({
        url: `/dragons/caves/delete-by-max-depth`,
        method: 'DELETE',
      }),
    }),
    killDragon: build.mutation<KillDragonApiResponse, KillDragonApiArg>({
      query: (queryArg) => ({
        url: `/dragons/persons/${queryArg['person-id']}/kill/${queryArg['dragon-id']}`,
        method: 'POST',
      }),
    }),
    getCoordinates: build.query<
      GetCoordinatesApiResponse,
      GetCoordinatesApiArg
    >({
      query: () => ({ url: `/dragons/coordinates` }),
    }),
    getCaves: build.query<GetCavesApiResponse, GetCavesApiArg>({
      query: () => ({ url: `/dragons/caves` }),
    }),
    getPersons: build.query<GetPersonsApiResponse, GetPersonsApiArg>({
      query: () => ({ url: `/dragons/persons` }),
    }),
    getHeads: build.query<GetHeadsApiResponse, GetHeadsApiArg>({
      query: () => ({ url: `/dragons/heads` }),
    }),
    getLocations: build.query<GetLocationsApiResponse, GetLocationsApiArg>({
      query: () => ({ url: `/dragons/locations` }),
    }),
    reassignAndDeleteDragon: build.mutation<
      ReassignAndDeleteDragonApiResponse,
      ReassignAndDeleteDragonApiArg
    >({
      query: (queryArg) => ({
        url: `/dragons/${queryArg.id}/reassign-and-delete`,
        method: 'POST',
        body: queryArg.body,
      }),
    }),
  }),
  overrideExisting: false,
});
export { injectedRtkApi as dragonsApi };
export type CreateDragonApiResponse = unknown;
export type CreateDragonApiArg = {
  /** Dragon to create */
  dragonCreate: DragonCreate;
};
export type UpdateDragonApiResponse = unknown;
export type UpdateDragonApiArg = {
  /** Dragon to update */
  dragon: Dragon;
};
export type GetDragonByIdApiResponse = /** status 200 OK */ Dragon;
export type GetDragonByIdApiArg = {
  /** Dragon ID */
  id: number;
};
export type DeleteDragonByIdApiResponse = unknown;
export type DeleteDragonByIdApiArg = {
  /** Dragon ID */
  id: number;
};
export type GetDragonsApiResponse = /** status 200 OK */ {
  dragons?: Dragon[];
  total_page_count?: number;
  total_dragons_count?: number;
};
export type GetDragonsApiArg = {
  /** Filters */
  body: {
    pagination?: Pagination;
    sorting?: SortingList;
    dragon?: DragonFilter;
  };
};
export type DeleteDragonByAgeApiResponse = unknown;
export type DeleteDragonByAgeApiArg = {
  /** Dragon age */
  age: number;
};
export type GetDragonsAgeSumApiResponse = /** status 200 OK */ number;
export type GetDragonsAgeSumApiArg = void;
export type GetMaxTypeDragonApiResponse = /** status 200 OK */ Dragon;
export type GetMaxTypeDragonApiArg = void;
export type DeleteDragonsInCaveWithMaxDepthApiResponse = unknown;
export type DeleteDragonsInCaveWithMaxDepthApiArg = void;
export type KillDragonApiResponse = unknown;
export type KillDragonApiArg = {
  /** Killer person ID */
  'person-id': number;
  /** Dragon ID */
  'dragon-id': number;
};
export type GetCoordinatesApiResponse = /** status 200 OK */ Coordinates[];
export type GetCoordinatesApiArg = void;
export type GetCavesApiResponse = /** status 200 OK */ DragonCave[];
export type GetCavesApiArg = void;
export type GetPersonsApiResponse = /** status 200 OK */ Person[];
export type GetPersonsApiArg = void;
export type GetHeadsApiResponse = /** status 200 OK */ DragonHead[];
export type GetHeadsApiArg = void;
export type GetLocationsApiResponse = /** status 200 OK */ Location[];
export type GetLocationsApiArg = void;
export type ReassignAndDeleteDragonApiResponse = unknown;
export type ReassignAndDeleteDragonApiArg = {
  /** ID of the dragon to delete */
  id: number;
  /** IDs of dragons to reassign dependencies to. */
  body: {
    /** ID of the dragon to which all dependencies (killer, cave, coordinates, head) will be reassigned. */
    newOwnerId: number;
  };
};
export type DragonCreateCoordinates = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  x?: number;
  y?: number;
};
export type DragonCreateCave = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  depth?: number;
  numberOfTreasures?: number;
};
export type Color = 'GREEN' | 'RED' | 'WHITE' | 'BROWN';
export type Location = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  x: number;
  y: number;
  name?: string | null;
};
export type DragonCreatePerson = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  name?: string;
  eyeColor?: Color;
  hairColor?: Color | null;
  location?: Location | null;
  /** Must be greater than 0 if present.
   */
  height?: number | null;
  weight?: number;
  /** Unique passport identifier. Must not be null.
   */
  passportID?: string;
};
export type DragonType = 'WATER' | 'UNDERGROUND' | 'AIR' | 'FIRE';
export type DragonCharacter =
  | 'CUNNING'
  | 'WISE'
  | 'GOOD'
  | 'CHAOTIC_EVIL'
  | 'FICKLE';
export type DragonCreateHead = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  toothCount?: number;
};
export type DragonCreate = {
  /** Must not be null or empty. */
  name: string;
  /** Either a DragonCreateCoordinates object or an ID reference to existing coordinates */
  coordinates?: DragonCreateCoordinates | number;
  /** Either a DragonCreateCave object or an ID reference to existing cave */
  cave?: DragonCreateCave | number;
  /** Either a DragonCreatePerson object or an ID reference to existing person */
  killer?: (DragonCreatePerson | number) | null;
  /** Must not be null and greater than 0. */
  age: number;
  color: Color;
  type: DragonType;
  character?: DragonCharacter | null;
  /** Either a DragonCreateHead object or an ID reference to existing head */
  head?: (DragonCreateHead | number) | null;
};
export type Coordinates = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  x: number;
  y: number;
};
export type DragonCave = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  depth: number;
  numberOfTreasures: number;
};
export type Person = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  name: string;
  eyeColor: Color;
  hairColor?: Color | null;
  location?: Location | null;
  /** Must be greater than 0 if present.
   */
  height?: number | null;
  weight: number;
  /** Unique passport identifier. Must not be null.
   */
  passportID: string;
};
export type DragonHead = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  toothCount: number;
};
export type Dragon = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id: number;
  /** Must not be null or empty. */
  name: string;
  coordinates: Coordinates;
  /** Auto-generated creation date.   Must not be null.
   */
  creationDate: string;
  cave: DragonCave;
  killer?: Person | null;
  /** Must not be null and greater than 0. */
  age: number;
  color: Color;
  type: DragonType;
  character?: DragonCharacter | null;
  head?: DragonHead;
};
export type Pagination = {
  /** Номер страницы */
  page?: number;
  /** Размер страницы */
  size?: number;
};
export type SortingColumn =
  | 'ID'
  | 'NAME'
  | 'COORDINATES_X'
  | 'COORDINATES_Y'
  | 'CREATION_DATE'
  | 'CAVE_DEPTH'
  | 'CAVE_NUMBER_OF_TREASURES'
  | 'PERSON_NAME'
  | 'PERSON_EYE_COLOR'
  | 'PERSON_HAIR_COLOR'
  | 'PERSON_LOCATION_X'
  | 'PERSON_LOCATION_Y'
  | 'PERSON_LOCATION_NAME'
  | 'PERSON_HEIGHT'
  | 'PERSON_WEIGHT'
  | 'PERSON_PASSPORT_ID'
  | 'AGE'
  | 'COLOR'
  | 'DRAGON_TYPE'
  | 'CHARACTER'
  | 'HEAD_TOOTH_COUNT';
export type SortingDirection = 'ASC' | 'DESC';
export type Sorting = {
  column: SortingColumn;
  direction: SortingDirection;
};
export type SortingList = Sorting[];
export type CoordinatesFilter = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  x?: number;
  y?: number;
};
export type DateRangeFilter = {
  /** Start date and time. */
  from?: string;
  /** End date and time. */
  to?: string;
};
export type DragonCaveFilter = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  depth?: number;
  numberOfTreasures?: number;
};
export type LocationFilter = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  x?: number;
  y?: number;
  name?: string | null;
};
export type PersonFilter = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  name?: string;
  eyeColor?: Color;
  hairColor?: Color | null;
  location?: LocationFilter | null;
  /** Must be greater than 0 if present.
   */
  height?: number | null;
  weight?: number;
  /** Unique passport identifier. Must not be null.
   */
  passportID?: string;
};
export type DragonHeadFilter = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  toothCount?: number;
};
export type DragonFilter = {
  /** Auto-generated unique ID.   Must not be null and must be greater than 0.
   */
  id?: number;
  /** Must not be null or empty. */
  name?: string;
  coordinates?: CoordinatesFilter;
  creationDateRange?: DateRangeFilter;
  cave?: DragonCaveFilter;
  killer?: PersonFilter | null;
  /** Must not be null and greater than 0. */
  age?: number;
  color?: Color;
  type?: DragonType;
  character?: DragonCharacter | null;
  head?: DragonHeadFilter;
};
export const {
  useCreateDragonMutation,
  useUpdateDragonMutation,
  useGetDragonByIdQuery,
  useDeleteDragonByIdMutation,
  useGetDragonsQuery,
  useDeleteDragonByAgeMutation,
  useGetDragonsAgeSumQuery,
  useGetMaxTypeDragonQuery,
  useDeleteDragonsInCaveWithMaxDepthMutation,
  useKillDragonMutation,
  useGetCoordinatesQuery,
  useGetCavesQuery,
  useGetPersonsQuery,
  useGetHeadsQuery,
  useGetLocationsQuery,
  useReassignAndDeleteDragonMutation,
} = injectedRtkApi;
