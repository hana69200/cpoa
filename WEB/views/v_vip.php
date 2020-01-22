<?php require_once(PATH_VIEWS.'header.php');?>

<!--  Zone message d'alerte -->
<?php require_once(PATH_VIEWS.'alert.php');?>


<?php
if (!isset($alert)) { // S'il n'y a pas eu d'erreurs
?>
<!-- Affichage du formulaire -->
	
	
	<?php
if($vip!=null){
	echo '<div class = "row"><table class="table"><thead class="thead-dark"><tr><th scope="col">ID</th><th scope="col">Nom</th><th scope="col">Prénom</th></tr></thead><tbody>';
	foreach ($vip as $vips) {
		echo '<td><a href="index.php?page=detailVIP&id=';
		echo $vips->getVIPID();
		echo '" style="display:block;width:100%;height:100%" >'.$vips->getVIPID().'</a></td>';
		echo '<td><a href="index.php?page=detailVIP&id=';
		echo $vips->getVIPID();
		echo '" style="display:block;width:100%;height:100%;">'.$vips->getVIPNOM().'</a></td><td><a href="index.php?page=detailVIP&id=';
		echo $vips->getVIPID();
		echo '" style="display:block;width:100%;height:100%">'.$vips->getVIPRENOM().'</a></tr>';
	}
	echo '</tbody></table></div>';
?>
	<form action="index.php?page=ajouterVIP" method="post">
		<button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Ajouter un VIP</button>
	</form>
<?php }

else{ ?>
	<script>alert("<?php echo htmlspecialchars('Aucun VIP ne correspond à cette recherche', ENT_QUOTES); ?>")</script>
	<?php
	header('Refresh:0; url=index.php');
 } 
}
?>

<?php require_once(PATH_VIEWS.'footer.php'); 