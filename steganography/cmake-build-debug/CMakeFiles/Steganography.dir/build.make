# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.10

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /opt/clion-2018.1.5/bin/cmake/bin/cmake

# The command to remove a file.
RM = /opt/clion-2018.1.5/bin/cmake/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/Steganography.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Steganography.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Steganography.dir/flags.make

CMakeFiles/Steganography.dir/main.c.o: CMakeFiles/Steganography.dir/flags.make
CMakeFiles/Steganography.dir/main.c.o: ../main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/Steganography.dir/main.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/Steganography.dir/main.c.o   -c /home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography/main.c

CMakeFiles/Steganography.dir/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/Steganography.dir/main.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography/main.c > CMakeFiles/Steganography.dir/main.c.i

CMakeFiles/Steganography.dir/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/Steganography.dir/main.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography/main.c -o CMakeFiles/Steganography.dir/main.c.s

CMakeFiles/Steganography.dir/main.c.o.requires:

.PHONY : CMakeFiles/Steganography.dir/main.c.o.requires

CMakeFiles/Steganography.dir/main.c.o.provides: CMakeFiles/Steganography.dir/main.c.o.requires
	$(MAKE) -f CMakeFiles/Steganography.dir/build.make CMakeFiles/Steganography.dir/main.c.o.provides.build
.PHONY : CMakeFiles/Steganography.dir/main.c.o.provides

CMakeFiles/Steganography.dir/main.c.o.provides.build: CMakeFiles/Steganography.dir/main.c.o


# Object files for target Steganography
Steganography_OBJECTS = \
"CMakeFiles/Steganography.dir/main.c.o"

# External object files for target Steganography
Steganography_EXTERNAL_OBJECTS =

Steganography: CMakeFiles/Steganography.dir/main.c.o
Steganography: CMakeFiles/Steganography.dir/build.make
Steganography: CMakeFiles/Steganography.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable Steganography"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/Steganography.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Steganography.dir/build: Steganography

.PHONY : CMakeFiles/Steganography.dir/build

CMakeFiles/Steganography.dir/requires: CMakeFiles/Steganography.dir/main.c.o.requires

.PHONY : CMakeFiles/Steganography.dir/requires

CMakeFiles/Steganography.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/Steganography.dir/cmake_clean.cmake
.PHONY : CMakeFiles/Steganography.dir/clean

CMakeFiles/Steganography.dir/depend:
	cd /home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography /home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography /home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography/cmake-build-debug /home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography/cmake-build-debug /home/CS/users/cmaxel/.linux/COS232/cmaxel/course/cos232/project/steganography/cmake-build-debug/CMakeFiles/Steganography.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/Steganography.dir/depend
