module.exports = function(grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
// Task configuration will be written here
        bower: {
            install: {
                options: {
                    install: true,
                    copy: false,
                    targetDir: './vendor'/*,
                    cleanTargetDir: true*/
                }
            }
        },

        uglify: {
            dist: {
                files: {
                    'build/app/app.js': [ 'build/app/app.js' ]
                },
                options: {
                    mangle: false
                }
            }
        },

        html2js: {
            app:{
                options: {
                    base: 'src/app',
                    module: 'app.templates',
                    useStrict: true
                },
                src:  ['src/app/**/*.tpl.html'],
                dest: 'build/templates/templates.js'
            }
        },

        clean: {
            build: {
              src: ['build']
            },
            temp: {
                src: [ 'tmp' ]
            }
        },

        concat: {
            build: {
                src: ['src/app.js', 'src/app/**/*.js'],
                dest: 'build/app/app.js'
            }
        },

        jshint: {
            all: [ 'Gruntfile.js', 'app/*.js', 'app/**/*.js' ]
        },

        connect: {
            server: {
                options: {
                    hostname: 'localhost',
                    port: 8080
                }
            }
        },

        watch: {
            dev: {
                files: [ 'Gruntfile.js', 'app/*.js', '*.html' ],
                tasks: [ 'jshint', 'karma:unit', 'html2js:dist', 'concat:dist', 'clean:temp' ],
                options: {
                    atBegin: true
                }
            },
            min: {
                files: [ 'Gruntfile.js', 'app/*.js', '*.html' ],
                tasks: [ 'jshint', 'karma:unit', 'html2js:dist', 'concat:dist', 'clean:temp', 'uglify:dist' ],
                options: {
                    atBegin: true
                }
            }
        },

        compress: {
            dist: {
                options: {
                    archive: 'dist/<%= pkg.name %>-<%= pkg.version %>.zip'
                },
                files: [{
                    src: [ 'index.html' ],
                    dest: '/'
                }, {
                    src: [ 'dist/**' ],
                    dest: 'dist/'
                }, {
                    src: [ 'assets/**' ],
                    dest: 'assets/'
                }, {
                    src: [ 'vendor/**' ],
                    dest: 'vendor/'
                }]
            }
        },

        karma: {
            options: {
                configFile: 'config/karma.conf.js'
            },
            unit: {
                singleRun: true
            },

            continuous: {
                singleRun: false,
                autoWatch: true
            }
        },
        writefile: {
            options: {
                data: {
                    title: 'ЦОФ "Щедрухинская"'
                },
                paths: {
                    stylesheets: [
                        'vendor/bootstrap/dist/css/bootstrap.css',
                        'vendor/bootstrap/dist/css/bootstrap-theme.css',
                        'vendor/angular-material/angular-material.css',
                        'vendor/angular-bootstrap-lightbox/dist/angular-bootstrap-lightbox.min.css',
                        'src/style/main.css'
                    ],
                    scripts: [                        
                        'vendor/jquery/jquery.min.js',
                        'vendor/underscore/underscore.min.js',
                        'vendor/angular/angular.min.js',
                        'vendor/lodash/dist/lodash.min.js',
                        'vendor/angular-ui-router/release/angular-ui-router.min.js',
                        'vendor/angular-aria/angular-aria.min.js',
                        'vendor/angular-animate/angular-animate.min.js',
                        'vendor/angular-material/angular-material.min.js',
                        'vendor/angular-sanitize/angular-sanitize.min.js',
                        'vendor/angular-bootstrap/ui-bootstrap-tpls.min.js',
                        'vendor/angular-data-grid/dist/pagination.min.js',
                        'vendor/angular-data-grid/dist/dataGrid.min.js',
                        'vendor/angular-filter/dist/angular-filter.min.js',
                        'vendor/angular-bootstrap-lightbox/dist/angular-bootstrap-lightbox.min.js',
                        'app/app.js',
                        'templates/templates.js'
                    ]
                }
            },
            index: {
                src: 'src/index.html',
                dest: 'build/index.html'
            }
        },
        copy: {
            build_vendor_js: {
                files: [
                    {
                        src: [
                            'vendor/jquery/jquery.min.js',
                            'vendor/underscore/underscore.min.js',
                            'vendor/angular/angular.min.js',
                            'vendor/lodash/dist/lodash.min.js',
                            'vendor/angular-aria/angular-aria.min.js',
                            'vendor/angular-animate/angular-animate.min.js',
                            'vendor/angular-material/angular-material.min.js',
                            'vendor/angular-sanitize/angular-sanitize.min.js',
                            'vendor/angular-bootstrap/ui-bootstrap-tpls.min.js',
                            'vendor/angular-ui-router/release/angular-ui-router.min.js',
                            'vendor/angular-data-grid/dist/pagination.min.js',
                            'vendor/angular-data-grid/dist/dataGrid.min.js',
                            'vendor/angular-filter/dist/angular-filter.min.js',
                            'vendor/angular-bootstrap-lightbox/dist/angular-bootstrap-lightbox.min.js',
                            'app/app.js',
                            'templates/templates.js'
                        ],
                        dest: 'build/',
                        cwd: '.',
                        expand: true
                    }
                ]
            },
            build_vendor_css:{
                files: [{
                    src: [
                        'vendor/bootstrap/dist/css/*.css',
                        'vendor/angular-material/angular-material.css',
                        'vendor/angular-bootstrap-lightbox/dist/angular-bootstrap-lightbox.min.css'
                    ],
                    dest: 'build/',
                    cwd: '.',
                    expand: true
                }]
            },
            build_vendor_font:{
                files: [{
                    src: [
                        'vendor/bootstrap/dist/fonts/glyphicons-halflings-regular.svg',
                        'vendor/bootstrap/dist/fonts/glyphicons-halflings-regular.eot',
                        'vendor/bootstrap/dist/fonts/glyphicons-halflings-regular.ttf',
                        'vendor/bootstrap/dist/fonts/glyphicons-halflings-regular.woff'
                    ],
                    dest: 'build/',
                    cwd: '.',
                    expand: true
                }]
            },
            build_css:{
                files: [{
                    src: [
                        'src/style/main.css'
                    ],
                    dest: 'build/',
                    cwd: '.',
                    expand: true
                }]
            },
            build_img:{
                files: [{
                    src: [
                        'img/*',
                        'vendor/select2/select2.png',
                        'vendor/select2/select2-spinner.gif',
                        'web/vendor/scania-angular-select2/less/*',
                        'vendor/jquery-ui/themes/base/images/*'
                    ],
                    dest: 'build/',
                    cwd: '.',
                    expand: true
                }]
            },
            build_tpl:{
                files: [{
                    src: [
                        'static-html/*.tpl.html'
                    ],
                    dest: 'build/',
                    cwd: '.',
                    expand: true
                }]
            }
        }
    });
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-connect');
    grunt.loadNpmTasks('grunt-contrib-compress');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-html2js');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-bower-task');
    grunt.loadNpmTasks('grunt-writefile');
    grunt.loadNpmTasks('grunt-karma');
    // Loading of tasks and registering tasks will be written here

    grunt.registerTask('build', [
        'clean:build',
        'jshint',
        'concat',
        'html2js',
        'copy:build_vendor_js',
        'copy:build_vendor_css',
        'copy:build_vendor_font',
        'copy:build_img',
        'copy:build_css',
        'copy:build_tpl',
        'writefile'
    ]);
    grunt.registerTask('release', [
        'build',
        'uglify:dist'
    ]);
    grunt.registerTask('testTask', ['writefile:index']);
    grunt.registerTask('test', [ 'bower', 'jshint', 'karma:continuous' ]);
    grunt.registerTask('minified', [ 'bower', 'connect:server', 'watch:min' ]);
    grunt.registerTask('package', [ 'jshint', 'karma:unit', 'html2js:dist', 'concat:dist', 'uglify:dist',
        'clean:temp', 'compress:dist' ]);
};