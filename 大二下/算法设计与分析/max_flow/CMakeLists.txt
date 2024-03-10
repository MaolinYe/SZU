cmake_minimum_required(VERSION 3.23)
project(max_flow)

set(CMAKE_CXX_STANDARD 14)

add_executable(max_flow main.cpp Ford_Fulkerson.h Edmonds_karp.h Dinic.h)
