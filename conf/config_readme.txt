Configuration files readme
--------------------------

There are 3 types of configuration files for customising the generated logging:

1. *setter-config.xml - ignore generating setter methods for fields

2. *constructor-config.xml - non-default constructors for classes to initialise fields using
                             constructor parameters instead of setter methods
                             
3. *collection-config.xml - add elements to collection fields using adder methods in the enclosing
                            class instead of accessing the collection directly
                            
The Files:
----------

- setter-config.xml
- constructor-config.xml
- collection-config.xml

These versions are for configuring Java API classes or classes in other shared libraries. These also
include explanations of the xml configuration in the comments for each file.

- test-setter-config.xml
- test-constructor-config.xml
- test-collection-config.xml

These versions are only for running the unit tests.

- jpetstore-test-setter-config.xml

This is for running the JPetStore sample app which is used for the tutorial


Including the configuration files
---------------------------------

The configuration files will only be used if they are included in the properties file
'configuration.properties'.

For setter configuration files, they need to be added to the 'setter.config.files' key
e.g.
setter.config.files=setter-config.xml, test-setter-config.xml, jpetstore-test-setter-config.xml

For constructor configuration files, they need to be added to the 'constructor.config.files' key
e.g.
constructor.config.files=constructor-config.xml, test-constructor-config.xml

For collection configuration files, they need to be added to the 'collection.config.files' key
e.g.
collection.config.files=test-collection-config.xml