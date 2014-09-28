<?php
	header('Content-Type: text/plain; charset=utf-8');

	// 
	// @See http://cn2.php.net/manual/zh/features.file-upload.php
	//

	// $_FILES is a default variable array stored upload file.
	if ($_FILES["file"]["error"] != UPLOAD_ERR_OK) {
		// UPLOAD_ERR_OK
		// UPLOAD_ERR_NO_FILE
		// UPLOAD_ERR_FORM_SIZE
		echo "Error: " . $_FILES["file"]["error"] . "<br>";
	} else {
		echo "File Upload Success!<br>";
		echo "Upload: "    . $_FILES["file"]["name"]          . "<br>";
		echo "Type: "      . $_FILES["file"]["type"]          . "<br>";
		echo "Size: "      . ($_FILES["file"]["size"] / 1024) . " Kb<br>";
		echo "Stored in: " . $_FILES["file"]["tmp_name"]      . "<br>";
		echo "MIME_TYPE: " . $_FILES["file"]["mime"];
		$ext = $_FILES["file"]["ext"];
		// DO NOT TRUST $_FILES['upfile']['mime'] VALUE !!
	    // Check MIME Type by yourself.
		$finfo = new finfo(FILEINFO_MIME_TYPE);
		if (false === $ext = array_search(
			$finfo->file($_FILES['file']['tmp_name']),
			array(
				'jpg' => 'image/jpeg',
				'png' => 'image/png',
				'gif' => 'image/gif',
			),
			true
		)) {
			throw new RuntimeException('Invalid file format.');
		}
	}

	// You should name it uniquely.
	// DO NOT USE $_FILES['upfile']['name'] WITHOUT ANY VALIDATION !!
	// On this example, obtain safe unique name from its binary data.
	if (!move_uploaded_file(
		$_FILES['file']['tmp_name'],
		sprintf('./uploads/%s.%s',
			sha1_file($_FILES['upfile']['tmp_name']),
			$ext
		)
	)) {
		throw new RuntimeException('Failed to move uploaded file.');
	}