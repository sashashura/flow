/**
 * This file contains project specific customizations for the webpack build.
 * It is autogenerated if it didn't exist or if it was made for an older
 * incompatible version.
 *
 * Defaults are provided in an autogenerated webpack.generated.js file and used by this file.
 * The webpack.generated.js file is overwritten on each build and no customization can be done there.
 */
const merge = require('webpack-merge');
const flowDefaults = require('./webpack.generated.js');

/**
 * To change the webpack config, add a new configuration object in
 * the merge arguments below:
 */
module.exports = merge(flowDefaults,
    {
        stats: {
            logging: 'info',
            loggingDebug: [
                'ThemeLiveReloadPlugin',
                '/ThemeLiveReloadPlugin/'
            ]
        },
        infrastructureLogging: {
            level: 'info',
            debug: [
                '/.*Plugin.*/',
                'ThemeLiveReloadPlugin'
            ]
        },

    }
  // Override default configuration
  // {
  //   mode: 'development',
  //   devtool: 'inline-source-map',
  // },

  // Add a custom plugin
  // (install the plugin with `npm install --save-dev webpack-bundle-analyzer`)
  // {
  //   plugins: [
  //     new require('webpack-bundle-analyzer').BundleAnalyzerPlugin({
  //       analyzerMode: 'static'
  //     })
  //   ]
  // },
);
