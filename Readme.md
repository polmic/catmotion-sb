# Catmotion-sb

A SpringBoot Rest API/App to:
- gather videos from a filesystem
- serve a list of videos
- stream a selected video
- delete a video

## Use case

Serve the videos recorded by a video monitoring system installed on a Linux (Centos) server.   
The server records the videos using [motion](https://motion-project.github.io/) and stores them on its filesystem.

This app will gather the videos and store them in a *MongoDb* database in order to serve them to an [*Angular* front](https://github.com/polmic/catmotion-ng) on demand.
