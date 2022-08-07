export type CatType = {
  id: string;
  name: string;
  age: number;
  species: string;
  isCute: boolean;
  friends: string[];
};

export const Cat: CatType[] = [
  {
    id: "aaa",
    name: "AAA",
    age: 8,
    species: "Russian Blue",
    isCute: true,
    friends: ["aaa", "AAA"],
  },
  {
    id: "bbb",
    name: "BBB",
    age: 4,
    species: "Sphynx cat",
    isCute: true,
    friends: ["bbb", "BBB"],
  },
  {
    id: "ccc",
    name: "CCC",
    age: 1,
    species: "cat1",
    isCute: false,
    friends: ["ccc", "CCC"],
  },
];
