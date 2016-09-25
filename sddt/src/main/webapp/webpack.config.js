/**
 * Created by bondarm on 23.09.16.
 */

module.exports = {
    entry: './src/app.js',
    devtool: 'sourcemaps',
    cache: true,
    debug: true,
    output: {
        path: './resources/static/js/',
        filename: 'bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.js$/ ,
                exclude: /(node_modules)/,
                loader: 'babel-loader'
            }
        ]
    }
};