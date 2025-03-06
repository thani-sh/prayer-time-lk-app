import { readFile, writeFile } from 'node:fs/promises';

const files = ['hanafi.colombo.json', 'shafi.colombo.json'];

for (const filename of files) {
  const data = await readJSON(filename);
  for (let i = 0; i < data.length; i++) {
    for (let j = 0; j < data.length; j++) {
      data[i][j][4]++;
    }
  }
  await writeJSON(filename, data);
}

async function readJSON(filename) {
  const text = await readFile(filename, 'utf-8');
  return JSON.parse(text);
}

async function writeJSON(filename, data) {
  const text = JSON.stringify(data);
  await writeFile(filename, text);
}
