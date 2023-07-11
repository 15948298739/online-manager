# online-manager

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

### The problem
npm ERR! cb() never called!
```
管理员身份运行cmd

> npm install -g  npm-install-retry

> npm-install-retry --wait 500 --attempts 10 -- --production

删除node_modules和package-lock.json后，重新npm install即可
```

