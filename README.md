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
- Simple integration (in renderer) with Stuart Sierra's [Component](https://github.com/stuartsierra/component).
- Use of context bridge and IPC. There's a demo ping-pong two-way communication, and the main process also sends the date once a second which the renderer pushes on-screen via re-frame event. There's some scrappiness here: some background "threads" aren't totally cleaned up (that really needs Sierra Component utilised in the main process as well), and removing a single listener from an event in the renderer doesn't seem to work - possibly an issue with identity of ClojureScript functions.
