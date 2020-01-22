
<!-- Menu du site -->

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
				<?php if(isset($_SESSION['logged']) ){ ?>
				<li <?php echo ($page=='open' ? 'class="active"':'')?>>
				<a href="index.php">
						<?= MENU_ACCUEIL ?>
				</a>
				</li>
				<li <?php echo ($page=='vip' ? 'class="active"':'')?>>
					<a href="index.php?page=vip">
						<?= MENU_VIP ?>
					</a>
				</li> 
				<li <?php echo ($page=='ajouterVIP' ? 'class="active"':'')?>>
					<a href="index.php?page=ajouterVIP">
						<?= MENU_AJOUTVIP ?>
					</a>
				</li>
				
				<li <?php echo ($page=='echangeCRM' ? 'class="active"':'')?>>
					<a href="index.php?page=echangeCRM">
						<?= MENU_ECHANGE ?>
					</a>
				</li>
				<?php } ?>
	</ul>
	<ul class="nav navbar-nav navbar-right ">
			<?php if(isset($_SESSION['logged'])) { ?>
				<li <?php echo ($page=='deconnexion' ? 'class="active"':'')?>>
					<a href="index.php?page=deconnexion" >
						<?= MENU_DECONNEXION ?>
					</a>
				</li>
			<?php }else {  ?>	
				<li <?php echo ($page=='connexion' ? 'class="active"':'')?>>
					<a href="index.php?page=connexion" >
						<?= MENU_CONNEXION ?>
					</a>
				</li>
				<li <?php echo ($page=='inscription' ? 'class="active"':'')?>>
					<a href="index.php?page=inscription" >
						<?= MENU_INSCRIPTION ?>
					</a>
				</li>
				
			<?php } ?>
    </ul>
  </div>
</nav>


