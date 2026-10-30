// Package only the public learning guide. Keep local links in the source HTML;
// hosted documentation/source links point to the corresponding GitHub files.
const fs = require('node:fs');
const path = require('node:path');
const assert = require('node:assert/strict');
const root = path.resolve(__dirname, '..');
const repository = 'https://github.com/LamaBimal/leetcode-solutions/blob/main/';
let html = fs.readFileSync(path.join(root, 'docs/learning-lab.html'), 'utf8');
// Inline local modules for a portable, offline distribution.
html = html.replace(/<script src="(assets\/[^"]+)"><\/script>/g, (_, file) => '<script>' + fs.readFileSync(path.join(root, 'docs', file), 'utf8') + '</script>');
// Convert Java link prefixes before validating static hrefs: SOLID links use
// template interpolation, and algorithm links are assigned at runtime.
html = html.replaceAll('../src/main/java/com/learning/', repository + 'src/main/java/com/learning/');
html = html.replace(/href="([^"#]+)"/g, (attribute, target) => {
    if (/^https?:/.test(target)) return attribute;
    const [file, fragment] = target.split('#');
    const absolute = path.resolve(root, 'docs', file);
    const relative = path.relative(root, absolute).split(path.sep).join('/');
    assert(!relative.startsWith('../') && fs.existsSync(absolute), `Invalid site link: ${target}`);
    return `href="${repository}${relative}${fragment ? '#' + fragment : ''}"`;
});
assert(!html.includes('../src/'), 'A dynamic source link was not converted.');
assert(html.includes('<title>Pattern Lab'), 'Site title missing.');
const output = path.join(root, '.cache/pages-site');
fs.mkdirSync(output, {recursive: true});
fs.writeFileSync(path.join(output, 'index.html'), html);
console.log('Packaged Pattern Lab at .cache/pages-site/index.html');
