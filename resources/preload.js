// Not sure there's a Shadow CLJS build type to generate a raw script, so:

const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('versions', {
    // Is this doing closure capture?
    node: () => process.versions.node,
    chrome: () => process.versions.chrome,
    electron: () => process.versions.electron
});

contextBridge.exposeInMainWorld('api', {
    // Bidirectional, invoked (asynchronously, method call) from renderer, handled via .handle callback in main:
    ping: () => ipcRenderer.invoke('ping'),

    // Unidirectional, invoked asynchronously from main, calls into renderer window declared callback, no reply:
    handleDate: (callback) => ipcRenderer.on('date', callback),
    removeHandleDate: (callback) => {
        ipcRenderer.removeListener('date', callback);
        // Callback identity doesn't seem to work, so...
        ipcRenderer.removeAllListeners('date');
    }
});
