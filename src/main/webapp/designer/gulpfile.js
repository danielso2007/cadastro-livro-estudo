'use strict';

var gulp = require('gulp');
var $ = require('gulp-load-plugins')();
var openURL = require('open');
var lazypipe = require('lazypipe');
var wiredep = require('wiredep').stream;
var runSequence = require('run-sequence');
var inject = require('gulp-inject');
var debug = require('gulp-debug');

var yeoman = {
    app: require('./bower.json').appPath || '../app',
    dist: 'dist'
};

var paths = {
    scripts: [yeoman.app + '/restrict/scripts/**/*.js', yeoman.app + '/public/scripts/**/*.js'],
    styles: [yeoman.app + '/styles/**/*.css'],
    test: ['test/spec/**/*.js'],
    testRequire: [
        'bower_components/angular/angular.js',
        'bower_components/angular-mocks/angular-mocks.js',
        'bower_components/angular-resource/angular-resource.js',
        'bower_components/angular-cookies/angular-cookies.js',
        'bower_components/angular-sanitize/angular-sanitize.js',
        'bower_components/angular-route/angular-route.js',
        'test/mock/**/*.js',
        'test/spec/**/*.js'
    ],
    karma: 'karma.conf.js',
    views: {
        main: yeoman.app + '/restrict/index.html',
        files: [yeoman.app + '/restrict/views/**/*.html']
    }
};

////////////////////////
// Reusable pipelines //
////////////////////////

var lintScripts = lazypipe()
    .pipe($.jshint, '.jshintrc')
    .pipe($.jshint.reporter, 'jshint-stylish');

gulp.task('lint:scripts', function() {
    return gulp.src(paths.scripts).pipe(lintScripts());
});

// inject bower components
gulp.task('bower', function() {
    var option = {
        directory: './bower_components',
        src: ['./bower_components/font-awesome/css/font-awesome.min.css'],
        ignorePath: '../../designer/bower_components',
        fileTypes: {
            html: {
                block: /(([ \t]*)<!--\s*bower:*(\S*)\s*-->)(\n|\r|.)*?(<!--\s*endbower\s*-->)/gi,
                detect: {
                    js: /<script.*src=['"]([^'"]+)/gi,
                    css: /<link.*href=['"]([^'"]+)/gi
                },
                replace: {
                    js: function(filePath) {
                        return '<script src="../lib' + filePath + '"></script>';
                    },
                    css: function(filePath) {
                        return '<link rel="stylesheet" type="text/css" href="../lib' + filePath + '"/>';
                    }
                }
            }
        }
    };

    return gulp.src(paths.views.main).pipe(wiredep(option)).pipe(gulp.dest(yeoman.app + '/restrict'));
});

gulp.task('inject', function() {
    var target = gulp.src(paths.views.main);

    function transform (filepath) {
        if (filepath.slice(-4) === '.css') {
          return '<link rel="stylesheet" href="' + filepath.replace('public/','') + '">';
        } else if (filepath.slice(-3) === '.js') {
          return '<script src="' + filepath.replace('public/','') + '"></script>';
        }
        return inject.transform.apply(inject.transform, arguments);
    };

    var sources = gulp.src([
      yeoman.app + '/public/vendor/**/*min.js',
      yeoman.app + '/public/vendor/**/*min.css',
      yeoman.app + '/public/styles/*.css',
    ], {read: false, cwd: "../app/", ignorePath: '../public'});

    var option = {relative: true, name: 'vendor', transform: transform};

    return target.pipe(inject(sources, option)).pipe(gulp.dest(yeoman.app + '/restrict'));
});

gulp.task('inject:scripts', function() {
    var target = gulp.src(paths.views.main);

    var sources = gulp.src([
      yeoman.app + '/restrict/scripts/*.js',
      yeoman.app + '/restrict/scripts/**/*.js'
    ], {read: false, cwd: "../app/"});

    var option = {relative: true, name: 'scripts'};

    return target.pipe(inject(sources, option)).pipe(gulp.dest(yeoman.app + '/restrict'));
});


gulp.task('images', function() {
    return gulp.src('./images/**/*')
        .pipe($.cache($.imagemin({
            optimizationLevel: 5,
            progressive: true,
            interlaced: true
        })))
        .pipe(gulp.dest(yeoman.app + '/public/images'));
});

gulp.task('copy:libs', function() {
    return gulp.copy([
    './bower_components/**/*.css',
    './bower_components/**/*.otf',
    './bower_components/**/*.eot',
    './bower_components/**/*.svg',
    './bower_components/**/*.ttf',
    './bower_components/**/*.woff',
    './bower_components/**/*.woff2',
    './bower_components/**/*.js'],
    yeoman.app + '/public/lib',
    'bower_components');
});

gulp.task('copy:vendor', function() {
    return gulp.copy([
    './vendor/**/*min.*'],
    yeoman.app + '/public/vendor',
    '');
});

gulp.task('copy:styles', function() {
    return gulp.copy([
    './styles/*.css'],
    yeoman.app + '/public/styles',
    '');
});

gulp.copy = function(src, dest, base) {
    return gulp.src(src, {
            base: base
        })
        .pipe(gulp.dest(dest)).pipe(debug());
};

gulp.task('default', ['bower'], function() {
    runSequence(['inject', 'images']);
});
