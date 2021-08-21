module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    proxy: {
      '/one': {
        target: 'http://localhost:8080/api',
        pathRewrite: {'^/one': ''}
      },
    }
  },
}
