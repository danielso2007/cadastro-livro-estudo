'use strict';

var gulp = require('gulp');
var $ = require('gulp-load-plugins')();
var openURL = require('open');
var lazypipe = require('lazypipe');
var wiredep = require('wiredep').stream;
var runSequence = require('run-sequence');
var inject = require('gulp-inject');

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

gulp.task('lint:scripts', function () {
  return gulp.src(paths.scripts).pipe(lintScripts());
});

// inject bower components
gulp.task('bower', function () {
  var option = {
               directory: yeoman.app + '/public/lib',
               bowerJson: './bower.json',
               src: [yeoman.app + '/public/lib' + '/font-awesome/css/font-awesome.min.css'],
               cwd: paths.views.main,
//               ignorePath: 'public/'
             };

  return gulp.src(paths.views.main).pipe(wiredep(option)).pipe(gulp.dest(yeoman.app + '/restrict'));
});

gulp.task('inject', function () {
  var target = gulp.src(paths.views.main);
  var sources = gulp.src(['./vendor/**/*min.js', './vendor/**/*min.css', './styles/**/*.css', yeoman.app + '/restrict/scripts/*.js', yeoman.app + '/restrict/scripts/**/*.js'], {read: false});
  var option = {relative: true};

  return target.pipe(inject(sources, option)).pipe(gulp.dest(yeoman.app + '/restrict'));
});


gulp.task('images', function () {
  return gulp.src('./images/**/*')
    .pipe($.cache($.imagemin({
        optimizationLevel: 5,
        progressive: true,
        interlaced: true
    })))
    .pipe(gulp.dest(yeoman.app + '/public/images'));
});

gulp.task('copy:libs', function () {
  return gulp.copy(['./bower_components/**/*.css', './bower_components/**/*.js'], yeoman.app + '/public/lib');
});

gulp.copy=function(src,dest){
    return gulp.src(src, {base:"bower_components"})
    .pipe(gulp.dest(dest));
};

gulp.task('default', ['bower'], function () {
  runSequence(['inject', 'images']);
});
