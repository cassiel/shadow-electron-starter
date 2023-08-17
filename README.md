# shadow-electron-starter
ClojureScript + Shadow-cljs + Electron + Reagent

## How to Run
```
npm install

npm run dev
npx electron .
```

## Release
```
npm run build
npx electron-packager . HelloWorld --platform=darwin --arch=x64
```

## Updates in Cassiel Fork

- Added `cider-nrepl` dependency so that we can jack in from Emacs.
- Replaced vanilla Reagent interaction with a basic re-frame setup.
