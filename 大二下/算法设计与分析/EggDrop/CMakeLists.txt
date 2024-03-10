cmake_minimum_required(VERSION 3.23)
project(EggDrop)

set(CMAKE_CXX_STANDARD 14)

add_executable(EggDrop main.cpp brute.h dynamicProgram.h dichotomy.h backward.h iterativeDP.h)
