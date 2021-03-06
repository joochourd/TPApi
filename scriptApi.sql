USE [master]
GO
/****** Object:  Database [api]    Script Date: 19/10/2018 9:16:08 p. m. ******/
CREATE DATABASE [api]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'api', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\api.mdf' , SIZE = 4160KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'api_log', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\api_log.ldf' , SIZE = 1040KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [api] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [api].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [api] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [api] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [api] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [api] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [api] SET ARITHABORT OFF 
GO
ALTER DATABASE [api] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [api] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [api] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [api] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [api] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [api] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [api] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [api] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [api] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [api] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [api] SET  ENABLE_BROKER 
GO
ALTER DATABASE [api] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [api] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [api] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [api] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [api] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [api] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [api] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [api] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [api] SET  MULTI_USER 
GO
ALTER DATABASE [api] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [api] SET DB_CHAINING OFF 
GO
ALTER DATABASE [api] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [api] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [api]
GO
/****** Object:  Table [dbo].[clientes]    Script Date: 19/10/2018 9:16:08 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[clientes](
	[nombre] [varchar](50) NOT NULL,
	[domicilio] [varchar](50) NOT NULL,
	[telefono] [varchar](50) NOT NULL,
	[dniCuit] [int] NOT NULL,
	[facturaNumero] [int] NULL,
 CONSTRAINT [pk_clientes] PRIMARY KEY CLUSTERED 
(
	[dniCuit] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[empleados]    Script Date: 19/10/2018 9:16:08 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[empleados](
	[nombreUsr] [varchar](50) NOT NULL,
	[keyword] [varchar](50) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[nroLU] [int] NULL,
	[fechaNacim] [date] NOT NULL,
	[idRolOriginal] [int] NOT NULL,
	[idRolTemporal] [int] NULL,
 CONSTRAINT [pk_empleados] PRIMARY KEY CLUSTERED 
(
	[nombreUsr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[facturas]    Script Date: 19/10/2018 9:16:08 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[facturas](
	[numero] [int] NOT NULL,
	[tipo] [varchar](10) NOT NULL,
	[fecha] [date] NOT NULL,
	[codItemFacturas] [int] NULL,
	[total] [float] NOT NULL,
 CONSTRAINT [pk_facturas] PRIMARY KEY CLUSTERED 
(
	[numero] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[itemFacturas]    Script Date: 19/10/2018 9:16:08 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[itemFacturas](
	[codItemFacturas] [int] IDENTITY(1,1) NOT NULL,
	[idFacturas] [int] NULL,
	[productoCodigoPublicacion] [int] NOT NULL,
	[cantidad] [int] NULL,
 CONSTRAINT [pk_itemFacturas] PRIMARY KEY CLUSTERED 
(
	[codItemFacturas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[productos]    Script Date: 19/10/2018 9:16:08 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[productos](
	[codigoPublicacion] [int] IDENTITY(1,1) NOT NULL,
	[titulo] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[precio] [float] NULL,
 CONSTRAINT [pk_productos] PRIMARY KEY CLUSTERED 
(
	[codigoPublicacion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[productosReclamos]    Script Date: 19/10/2018 9:16:08 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[productosReclamos](
	[idReclamo] [int] NOT NULL,
	[productoCodigoPublicacion] [int] NOT NULL,
	[Cantidad] [int] NOT NULL,
 CONSTRAINT [pk_productosReclamos] PRIMARY KEY CLUSTERED 
(
	[productoCodigoPublicacion] ASC,
	[Cantidad] ASC,
	[idReclamo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[reclamos]    Script Date: 19/10/2018 9:16:08 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[reclamos](
	[idReclamo] [int] IDENTITY(1,1) NOT NULL,
	[fecha] [date] NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[estados] [varchar](50) NOT NULL,
	[tipo] [varchar](50) NOT NULL,
	[fechaFacturacion] [date] NULL,
	[nroFactura] [int] NULL,
	[zona] [varchar](50) NULL,
	[clienteDniCuit] [int] NOT NULL,
	[empleadoNomUsr] [varchar](50) NOT NULL,
 CONSTRAINT [pk_reclamos] PRIMARY KEY CLUSTERED 
(
	[idReclamo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[roles]    Script Date: 19/10/2018 9:16:08 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[roles](
	[idRoles] [int] IDENTITY(1,1) NOT NULL,
	[descripcion] [varchar](50) NULL,
 CONSTRAINT [pk_roles] PRIMARY KEY CLUSTERED 
(
	[idRoles] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[clientes]  WITH CHECK ADD  CONSTRAINT [fk_clientes] FOREIGN KEY([facturaNumero])
REFERENCES [dbo].[facturas] ([numero])
GO
ALTER TABLE [dbo].[clientes] CHECK CONSTRAINT [fk_clientes]
GO
ALTER TABLE [dbo].[empleados]  WITH CHECK ADD  CONSTRAINT [fk_empleados1] FOREIGN KEY([idRolOriginal])
REFERENCES [dbo].[roles] ([idRoles])
GO
ALTER TABLE [dbo].[empleados] CHECK CONSTRAINT [fk_empleados1]
GO
ALTER TABLE [dbo].[empleados]  WITH CHECK ADD  CONSTRAINT [fk_empleados2] FOREIGN KEY([idRolTemporal])
REFERENCES [dbo].[roles] ([idRoles])
GO
ALTER TABLE [dbo].[empleados] CHECK CONSTRAINT [fk_empleados2]
GO
ALTER TABLE [dbo].[facturas]  WITH CHECK ADD  CONSTRAINT [fk_facturas] FOREIGN KEY([codItemFacturas])
REFERENCES [dbo].[itemFacturas] ([codItemFacturas])
GO
ALTER TABLE [dbo].[facturas] CHECK CONSTRAINT [fk_facturas]
GO
ALTER TABLE [dbo].[itemFacturas]  WITH CHECK ADD  CONSTRAINT [fk_itemFacturas] FOREIGN KEY([productoCodigoPublicacion])
REFERENCES [dbo].[productos] ([codigoPublicacion])
GO
ALTER TABLE [dbo].[itemFacturas] CHECK CONSTRAINT [fk_itemFacturas]
GO
ALTER TABLE [dbo].[productosReclamos]  WITH CHECK ADD  CONSTRAINT [fk_prodcutosReclamos2] FOREIGN KEY([productoCodigoPublicacion])
REFERENCES [dbo].[productos] ([codigoPublicacion])
GO
ALTER TABLE [dbo].[productosReclamos] CHECK CONSTRAINT [fk_prodcutosReclamos2]
GO
ALTER TABLE [dbo].[productosReclamos]  WITH CHECK ADD  CONSTRAINT [fk_productosReclamos1] FOREIGN KEY([idReclamo])
REFERENCES [dbo].[reclamos] ([idReclamo])
GO
ALTER TABLE [dbo].[productosReclamos] CHECK CONSTRAINT [fk_productosReclamos1]
GO
ALTER TABLE [dbo].[reclamos]  WITH CHECK ADD  CONSTRAINT [fk_reclamos1] FOREIGN KEY([clienteDniCuit])
REFERENCES [dbo].[clientes] ([dniCuit])
GO
ALTER TABLE [dbo].[reclamos] CHECK CONSTRAINT [fk_reclamos1]
GO
ALTER TABLE [dbo].[reclamos]  WITH CHECK ADD  CONSTRAINT [fk_reclamos2] FOREIGN KEY([empleadoNomUsr])
REFERENCES [dbo].[empleados] ([nombreUsr])
GO
ALTER TABLE [dbo].[reclamos] CHECK CONSTRAINT [fk_reclamos2]
GO
USE [master]
GO
ALTER DATABASE [api] SET  READ_WRITE 
GO
