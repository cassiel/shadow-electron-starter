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
- Replaced vanilla Reagent interaction with a basic [re-frame](https://day8.github.io/re-frame/) setup.
- Simple integration with Stuart Sierra's [Component](https://github.com/stuartsierra/component) - which isn't actually doing anything, but this shows linkage into shadow-cljs's lifecycle.
