#!/bin/bash

mvn exec:java -D  "exec.mainClass=pl.lumido.oblechproject.gui.Admin"
pkill java
