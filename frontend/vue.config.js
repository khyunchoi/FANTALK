module.exports = {
  devServer: {
    https: false,
    port: 8083,
    open: true,
    proxy: {
      '/api/v1': {
        target: 'http://localhost:8080/'
      }
    },
    historyApiFallback: true,
    hot: true
  },
  css: {
    requireModuleExtension: false // import 시에 경로에 .module 포함 안해도 됨.
  },
  transpileDependencies: [
    'vuetify'
  ],
  lintOnSave: false,
  outputDir: '../backend-java/src/main/resources/dist'
}
