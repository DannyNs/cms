const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const WebpackCleanupPlugin = require('webpack-cleanup-plugin')

module.exports = {
    entry: {
        'app': './src/index.tsx'
    },
    output: {
        path: path.resolve(__dirname, 'public'),
        filename: '[name]-[hash].bundle.js',
    },
    devtool: "source-map",
    resolve: {
        extensions: [".ts", ".tsx", ".js"]
    },
    module: {
        loaders: [
            {
                test: /\.tsx?$/,
                loader: "awesome-typescript-loader"
            },
            {
                test: /\.css$/,
                loader: "style-loader!css-loader"
            }
        ]
    },
    plugins: [
        new WebpackCleanupPlugin(),
        new HtmlWebpackPlugin({
            template: 'src/index.html'
        })        
    ]
};