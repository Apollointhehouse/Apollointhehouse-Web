import { PurgeCSS } from "purgecss";
import { createHash } from "node:crypto";
import { readdir, readFile, writeFile, rm } from "node:fs/promises";
import { join } from "node:path";

const OUT = process.argv[2] ?? "./out";
const CSS_DIR = join(OUT, "assets", "css");

const walk = async (dir) =>
  (await readdir(dir, { withFileTypes: true, recursive: true }))
    .filter((e) => e.isFile())
    .map((e) => join(e.parentPath, e.name));

const htmlFiles = (await walk(OUT)).filter((f) => f.endsWith(".html"));
const source = (await readdir(CSS_DIR)).find((f) => /^pico\.min\.[0-9a-f]+\.css$/.test(f));
if (!source) throw new Error(`No hashed pico.min.*.css in ${CSS_DIR}`);

const oldPath = join(CSS_DIR, source);
const original = await readFile(oldPath, "utf8");

const [result] = await new PurgeCSS().purge({
  content: htmlFiles,
  css: [{ raw: original }],
  variables: true,
  keyframes: true,
  safelist: { standard: [/^htmx-/], greedy: [/data-theme/], variables: [] },
});

const purged = result.css;
if (purged.length < 1000 || purged.length >= original.length) {
  throw new Error(`Purge looks wrong: ${original.length} -> ${purged.length} bytes`);
}

const hash = createHash("sha256").update(purged).digest("hex").slice(0, 10);
const newName = `pico.min.${hash}.css`;
await writeFile(join(CSS_DIR, newName), purged);
await rm(oldPath);

let rewritten = 0;
for (const file of htmlFiles) {
  const html = await readFile(file, "utf8");
  if (!html.includes(source)) continue;
  await writeFile(file, html.replaceAll(source, newName));
  rewritten++;
}

console.log(`pico: ${original.length} -> ${purged.length} bytes (${newName}), rewrote ${rewritten} html files`);
