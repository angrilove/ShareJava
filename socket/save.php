<?php
	header('Content-Type: text/html; charset=utf-8');

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

		// Get file extension.
		$ext = pathinfo($_FILES["file"]["name"], PATHINFO_EXTENSION);
		if (!is_dir('./uploads/')) {
			mkdir('./uploads/');
		}
		// You should name it uniquely.
		// DO NOT USE $_FILES['upfile']['name'] WITHOUT ANY VALIDATION !!
		// On this example, obtain safe unique name from its binary data.
		if (!move_uploaded_file(
			$_FILES['file']['tmp_name'],
			sprintf('./uploads/%s.%s',
				// Or you can use md5_file.
				sha1_file($_FILES['file']['tmp_name']),
				$ext
			)
		)) {
			throw new RuntimeException('Failed to move uploaded file.');
		}

		echo "<br><br><h1>File Info:</h1><br>";
		print_r($_FILES); echo "<br><br>";
		print_r(pathinfo($_FILES["file"]["tmp_name"]));
	}