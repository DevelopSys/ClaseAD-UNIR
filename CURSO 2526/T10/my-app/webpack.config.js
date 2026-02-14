const path = require('path');
const TerserPlugin = require('terser-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const CssMinimizerPlugin = require('css-minimizer-webpack-plugin');
const Dotenv = require('dotenv-webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const TsconfigPathsPlugin = require('tsconfig-paths-webpack-plugin');
const WarningsToErrorsPlugin = require('warnings-to-errors-webpack-plugin');


module.exports = (env, argv) => ({
  entry: {
    bundle: 'index.tsx'
  },
  output: {
    path: path.resolve(__dirname, './target/classes/static'),
    filename: 'js/[name].js',
    publicPath: '/'
  },
  devtool: argv.mode === 'production' ? false : 'eval-source-map',
  performance: {
    maxEntrypointSize: 976000,
    maxAssetSize: 976000
  },
  optimization: {
    minimize: true,
    minimizer: [
      new TerserPlugin(),
      new CssMinimizerPlugin()
    ]
  },
  plugins: [
    new MiniCssExtractPlugin({
      filename: 'css/[name].css'
    }),
    new Dotenv({
      path: argv.mode === 'production' ? '.env' : '.env.development'
    }),
    new HtmlWebpackPlugin({
      template: './src/main/webapp/index.html'
    }),
    new CopyWebpackPlugin({
      patterns: [{
        from: './src/main/webapp/public'
      }],
    }),
    new WarningsToErrorsPlugin()
  ],
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        use: ['ts-loader']
      },
      {
        test: /\.scss$/,
        use: [
          argv.mode === 'production' ? MiniCssExtractPlugin.loader : 'style-loader',
          {
            loader: 'css-loader',
            options: {
              importLoaders: 1,
              sourceMap: true
            }
          },
          {
            loader: 'postcss-loader',
            options: {
              postcssOptions: {
                plugins: [
                  require('autoprefixer')
                ]
              },
              sourceMap: true
            }
          },
          {
            loader: 'sass-loader',
            options: { sourceMap: true }
          }
        ]
      },
      {
        test: /\.(woff2|woff|ttf|png|jpg|jpeg|gif|svg|webp)$/,
        type: 'asset'
      }
    ]
  },
  resolve: {
    plugins: [new TsconfigPathsPlugin({})],
    extensions: ['.js', '.jsx', '.ts', '.tsx', '.json']
  },
  devServer: {
    port: 3000,
    compress: true,
    historyApiFallback: {
      disableDotRule: true
    },
    hot: true,
    static: false,
    watchFiles: [
      'src/main/webapp/**',
    ]
  }
});
