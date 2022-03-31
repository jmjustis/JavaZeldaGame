#!/bin/bash
set -u -e
javac Game.java View.java Controller.java Model.java Brick.java Link.java Pot.java
java Game
