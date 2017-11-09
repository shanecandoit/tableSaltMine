# tableSaltMine

![screenshot showing 3 calculations](docs/sample1.png)

* perform basic calculations
  * using inline values
  * referencing other cells
* save to disk and reload without prompting
* uniquely identify document using sh256 hash

## Goals
* nest tables
* refernce ranges
* create functions inline
* online uploading for sharing (pastebin, github)
* built in git support, (if non-pgrammer users like it)

## Rationale
* excel is good and bad
  * good
    * user friendly
	* table is appropriate for some data, text is 1-D
	* people know how to use it
	* non-programmers can make incredible things with it
  * bad
    * versioning: what are the differences between these two docs
	* workbooks: just stick tables inside cells (using their hash and url)
	* programming requires vba, just use cells and text (clarity > performance)
	* opaque: make calculations transparent using a scrubbable timeline, helpful for debugging
* remake it without the bad parts, or with the bad parts fixed
