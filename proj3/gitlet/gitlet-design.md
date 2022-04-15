# Gitlet Design Document
author: Amy Mathews

## Design Document Guidelines

Please use the following format for your Gitlet design document. Your design
document should be written in markdown, a language that allows you to nicely 
format and style a text file. Organize your design document in a way that 
will make it easy for you or a course-staff member to read.  

## 1. Classes and Data Structures

Include here any class definitions. For each class list the instance
variables and static variables (if any). Include a ***brief description***
of each variable and its purpose in the class. Your explanations in
this section should be as concise as possible. Leave the full
explanation to the following sections. You may cut this section short
if you find your document is too wordy.

###Main.java
This class acts as the driver code to call the functions depending on what the user inputs.
It implements methods to set up persistance.

#### Fields
1. static final File CWD: A pointer to the current working directory of the program.
2. static final File GITLET_FOLDER: A pointer to the `GITLET` directory in the current working directory.

###Commit.java
This class is created to represent the commit instance. It has functions to serialize and 
deserialize information, map the SHA1 codes of the blobs to appropriate blobs in the blob folder, supports reading
and writing from its corresponding saved file. 

#### Fields
 1. static final File COMMIT_FOLDER: a pointer to the directory persisting commit instances.
 2. String _date: a string representing the date.
 3. String _time: a string representing the time.
 4. String _git_log: a string representing the commit log.
 5. String _sha1code: a string representing the commit id. 
 6. String _branch: a string representing the current branch.
 7. String _parent1: a string representing parent 1.
 8. String _parent2: a string representing parent 2.
 9. HashMap<String, String> _blob_mapper:  a hashmap that stores all the pointers to the blobs.

### Funcs.java
This is a class that would hold all the other functions within it and 
including the staging area.

#### Fields
1. static final File BLOB_FOLDER: a folder representing all the blobs that have been commited
2. static final File STAGE_FOLDER: a folder representing all the potential blobs to be added/removed.
3. static final File POTENTIAL_ADD_FOLDER: a folder that lies within the STAGE_FOLDER, but represents the blobs to be **potentially** added. 
4. static final File POTENTIAL_REMOVE_FOLDER: a folder that lies within the STAGE_FOLDER, but represents the blobs to be **potentially** removed.
 


## 2. Algorithms

This is where you tell us how your code works. For each class, include
a high-level description of the methods in that class. That is, do not
include a line-by-line breakdown of your code, but something you would
write in a javadoc comment above a method, ***including any edge cases
you are accounting for***. We have read the project spec too, so make
sure you do not repeat or rephrase what is stated there.  This should
be a description of how your code accomplishes what is stated in the
spec.
### Main.java
1. main(String[] args): This is the entry point of the program. It first checks to make sure that the input array is not empty. Then, it calls `setupPersistence` to create the `/.gitlet` and `/.gitlet/commit`, `/.gitlet/blobs`, `/.gitlet/stage` for persistance. Lastly, depending on the input argument, different functions are called to perform the operation.
2. setupPersistence(): If the directory for persisting dog instances does not exist yet, then make the directory. The directory `.gitlet` will be created at the same time when the `commit`, `blob` , and  `stage` directory is being made.

### Commit.java
1. Commit(String _date, String _time, String _git_log, String _parent1, String _parent2): it creates a commit object with specified parameters.
2. putshas(String name, String _sha1code): maps name to blob.
3. _(more functions not really sure yet)_

### Func.java
1. init() : tells the current folder we are in to start doing version control with that folder. 
2. add(String filename) : it adds the file to the staging area.
3. status(): tells the user of all the commits made,and if there are any untracked files in the staging area.
4. checkout(): allows the user to go back to a previous version. 
5. log(): gives the user a list of commit history.
6. _(more functions not really sure yet)_


The length of this section depends on the complexity of the task and
the complexity of your design. However, simple explanations are
preferred. Here are some formatting tips:

* For complex tasks, like determining merge conflicts, we recommend
  that you split the task into parts. Describe your algorithm for each
  part in a separate section. Start with the simplest component and
  build up your design, one piece at a time. For example, your
  algorithms section for Merge Conflicts could have sections for:

   * Checking if a merge is necessary.
   * Determining which files (if any) have a conflict.
   * Representing the conflict in the file.
  
* Try to clearly mark titles or names of classes with white space or
  some other symbols.

## 3. Persistence

Describe your strategy for ensuring that you don’t lose the state of your program
across multiple runs. Here are some tips for writing this section:

* This section should be structured as a list of all the times you
  will need to record the state of the program or files. For each
  case, you must prove that your design ensures correct behavior. For
  example, explain how you intend to make sure that after we call
       `java gitlet.Main add wug.txt`,
  on the next execution of
       `java gitlet.Main commit -m “modify wug.txt”`, 
  the correct commit will be made.
  
* A good strategy for reasoning about persistence is to identify which
  pieces of data are needed across multiple calls to Gitlet. Then,
  prove that the data remains consistent for all future calls.
  
* This section should also include a description of your .gitlet
  directory and any files or subdirectories you intend on including
  there.

### add [text]
- We add contents of the file aka ~blobs~ into the potential_add folder with the path `./gitlet/stage/potential_add`
- We add the contents of the file ~blobs~ into the blob folder that resides within the gitlet folder, if we decided to commit changes after the previous step.
- If we decided to remove the contents of the file ~blobs~ we add into the potential_remove folder with the path `./gitlet/stage/potential_remove`

### commit [text]
- We create a snapshot of the version of the files at that particular time. This snapshot contains metadata and stored in file in the folder commit with the path `.\gitlet\commit`.

### 

## 4. Design Diagram

Attach a picture of your design diagram illustrating the structure of your
classes and data structures. The design diagram should make it easy to 
visualize the structure and workflow of your program.
![](/Users/amymathews/Downloads/Design.pdf)
