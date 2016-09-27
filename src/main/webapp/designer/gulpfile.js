// Generated on 2016-09-21 using generator-angular 0.15.1
'use strict';

var gulp = require('gulp');
var $ = require('gulp-load-plugins')();
var openURL = require('open');
var lazypipe = require('lazypipe');
var rimraf = require('rimraf');
var wiredep = require('wiredep').stream;
var runSequence = require('run-sequence');
var inject = require('gulp-inject');

var yeoman = {
  app: require('./bower.json').appPath || '../app',
  dist: 'dist'
};

var paths = {
  scripts: [yeoman.app + '/scripts/**/*.js'],
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
    main: yeoman.app + '/index.html',
    files: [yeoman.app + '/views/**/*.html']
  }
};

////////////////////////
// Reusable pipelines //
////////////////////////

var lintScripts = lazypipe()
  .pipe($.jshint, '.jshintrc')
  .pipe($.jshint.reporter, 'jshint-stylish');

gulp.task('lint:scripts', function () {
  return gulp.src(paths.scripts).pipe(lintScripts());
});

// inject bower components
gulp.task('bower', function () {
  var option = {
               directory: './bower_components',
         //      ignorePath: '..',
               src: ['./bower_components/font-awesome/css/font-awesome.min.css'],
               html: {
                 block: /(([ \t]*)<!--\s*bower:*(\S*)\s*-->)(\n|\r|.)*?(<!--\s*endbower\s*-->)/gi,
                 detect: {
                   js: /<script.*src=['"]([^'"]+)/gi,
                   css: /<link.*href=['"]([^'"]+)/gi
                 },
                 replace: {
                   js: '<script src="{{filePath}}"></script>',
                   css: '<link href="{{filePath}}" rel="stylesheet" type="text/css" />'
                 }
               }
             };

  return gulp.src(paths.views.main)
    .pipe(wiredep(option)).pipe(gulp.dest(yeoman.app));
});

gulp.task('inject', function () {
  var target = gulp.src(paths.views.main);
  var sources = gulp.src(['./vendor/**/*min.js', './vendor/**/*min.css', './styles/**/*.css', yeoman.app + '/scripts/*.js', yeoman.app + '/scripts/**/*.js'], {read: false});
  var option = {relative: true};

  return target.pipe(inject(sources, option)).pipe(gulp.dest(yeoman.app));
});


//gulp.task('html', function () {
//  return gulp.src(yeoman.app + '/views/**/*')
//    .pipe(gulp.dest(yeoman.dist + '/views'));
//});

gulp.task('images', function () {
  return gulp.src('./images/**/*')
    .pipe($.cache($.imagemin({
        optimizationLevel: 5,
        progressive: true,
        interlaced: true
    })))
    .pipe(gulp.dest(yeoman.app + '/images'));
});

gulp.task('copy:extras', function () {
  return gulp.src(yeoman.app + '/*/.*', { dot: true }).pipe(gulp.dest(yeoman.dist));
});

gulp.copy=function(src,dest){
    return gulp.src(src, {base:"."}).pipe(gulp.dest(dest));
};

gulp.task('default', ['bower'], function () {
  runSequence(['inject', 'images']);
});
