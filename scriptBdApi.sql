USE [master]
GO
/****** Object:  Database [api]    Script Date: 16/11/2018 4:45:44 p. m. ******/
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
/****** Object:  Table [dbo].[actualizacionEstado]    Script Date: 16/11/2018 4:45:44 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[actualizacionEstado](
	[idActualizacionEstado] [int] IDENTITY(1,1) NOT NULL,
	[idReclamo] [int] NOT NULL,
	[fecha] [date] NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[estado] [varchar](50) NOT NULL,
	[empleadoNombreUsr] [varchar](50) NOT NULL,
 CONSTRAINT [pk_actualizacionEstado] PRIMARY KEY CLUSTERED 
(
	[idActualizacionEstado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[clientes]    Script Date: 16/11/2018 4:45:44 p. m. ******/
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
	[email] [varchar](50) NOT NULL,
 CONSTRAINT [pk_clientes] PRIMARY KEY CLUSTERED 
(
	[dniCuit] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[empleados]    Script Date: 16/11/2018 4:45:44 p. m. ******/
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
/****** Object:  Table [dbo].[facturas]    Script Date: 16/11/2018 4:45:44 p. m. ******/
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
	[dniCuitCliente] [int] NOT NULL,
 CONSTRAINT [pk_facturas] PRIMARY KEY CLUSTERED 
(
	[numero] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[itemFacturas]    Script Date: 16/11/2018 4:45:44 p. m. ******/
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
/****** Object:  Table [dbo].[productos]    Script Date: 16/11/2018 4:45:44 p. m. ******/
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
/****** Object:  Table [dbo].[productosReclamos]    Script Date: 16/11/2018 4:45:44 p. m. ******/
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
/****** Object:  Table [dbo].[reclamos]    Script Date: 16/11/2018 4:45:44 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[reclamos](
	[idReclamo] [int] IDENTITY(1,1) NOT NULL,
	[compuesto] [bit] NULL,
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
/****** Object:  Table [dbo].[roles]    Script Date: 16/11/2018 4:45:44 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[roles](
	[idRoles] [int] NOT NULL,
	[descripcion] [varchar](50) NULL,
 CONSTRAINT [pk_roles] PRIMARY KEY CLUSTERED 
(
	[idRoles] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[clientes] ([nombre], [domicilio], [telefono], [dniCuit], [email]) VALUES (N'emanuel', N'Av.Las Heras', N'011155859642', 1000001, N'emanuel@mail.com')
INSERT [dbo].[clientes] ([nombre], [domicilio], [telefono], [dniCuit], [email]) VALUES (N'emanuela', N'Av.9 de Julio', N'011155330902', 1000002, N'emanuela@mail.com')
INSERT [dbo].[clientes] ([nombre], [domicilio], [telefono], [dniCuit], [email]) VALUES (N'jorge', N'irigoyen', N'02920155179482', 1000003, N'jorge@mail.com')
INSERT [dbo].[clientes] ([nombre], [domicilio], [telefono], [dniCuit], [email]) VALUES (N'emanuel', N'Caseros', N'0299155659559', 1000004, N'ramon@mail.com')
INSERT [dbo].[clientes] ([nombre], [domicilio], [telefono], [dniCuit], [email]) VALUES (N'emanuel', N'25 de mayo', N'02945155630987', 1000005, N'rosa@mail.com')
INSERT [dbo].[empleados] ([nombreUsr], [keyword], [nombre], [nroLU], [fechaNacim], [idRolOriginal], [idRolTemporal]) VALUES (N'ana', N'789', N'ana', 3, CAST(0xC7150B00 AS Date), 3, 3)
INSERT [dbo].[empleados] ([nombreUsr], [keyword], [nombre], [nroLU], [fechaNacim], [idRolOriginal], [idRolTemporal]) VALUES (N'juan', N'123', N'juan', 1, CAST(0x92080B00 AS Date), 4, 4)
INSERT [dbo].[empleados] ([nombreUsr], [keyword], [nombre], [nroLU], [fechaNacim], [idRolOriginal], [idRolTemporal]) VALUES (N'laura', N'101', N'laura', 4, CAST(0x4A040B00 AS Date), 2, 1)
INSERT [dbo].[empleados] ([nombreUsr], [keyword], [nombre], [nroLU], [fechaNacim], [idRolOriginal], [idRolTemporal]) VALUES (N'maria', N'112', N'maria', 5, CAST(0xE2F60A00 AS Date), 5, 5)
INSERT [dbo].[empleados] ([nombreUsr], [keyword], [nombre], [nroLU], [fechaNacim], [idRolOriginal], [idRolTemporal]) VALUES (N'pedro', N'456', N'pedro', 2, CAST(0x21140B00 AS Date), 1, 2)
INSERT [dbo].[empleados] ([nombreUsr], [keyword], [nombre], [nroLU], [fechaNacim], [idRolOriginal], [idRolTemporal]) VALUES (N'pepe', N'131', N'pepe', 6, CAST(0xD3090B00 AS Date), 6, 6)
SET IDENTITY_INSERT [dbo].[productos] ON 

INSERT [dbo].[productos] ([codigoPublicacion], [titulo], [descripcion], [precio]) VALUES (1, N'Ford Focus', N'0km', 700000)
INSERT [dbo].[productos] ([codigoPublicacion], [titulo], [descripcion], [precio]) VALUES (2, N'Chevrolet Cruze', N'0km', 800000)
INSERT [dbo].[productos] ([codigoPublicacion], [titulo], [descripcion], [precio]) VALUES (3, N'Renault Sandero', N'80000km', 300800)
INSERT [dbo].[productos] ([codigoPublicacion], [titulo], [descripcion], [precio]) VALUES (4, N'Volkswagen Scirocco', N'5000km', 610400)
SET IDENTITY_INSERT [dbo].[productos] OFF
SET IDENTITY_INSERT [dbo].[reclamos] ON 

INSERT [dbo].[reclamos] ([idReclamo], [compuesto], [fecha], [descripcion], [estados], [tipo], [fechaFacturacion], [nroFactura], [zona], [clienteDniCuit], [empleadoNomUsr]) VALUES (6, NULL, CAST(0xF23E0B00 AS Date), N'competidor detectado', N'Registrado', N'zona', NULL, NULL, N'Recoleta', 1000001, N'juan')
INSERT [dbo].[reclamos] ([idReclamo], [compuesto], [fecha], [descripcion], [estados], [tipo], [fechaFacturacion], [nroFactura], [zona], [clienteDniCuit], [empleadoNomUsr]) VALUES (7, NULL, CAST(0xF23E0B00 AS Date), N'prueba', N'Registrado', N'facturacion', CAST(0xC73E0B00 AS Date), 1010, NULL, 1000002, N'juan')
SET IDENTITY_INSERT [dbo].[reclamos] OFF
INSERT [dbo].[roles] ([idRoles], [descripcion]) VALUES (1, N'responsableFacturacion')
INSERT [dbo].[roles] ([idRoles], [descripcion]) VALUES (2, N'responsableDistribucion')
INSERT [dbo].[roles] ([idRoles], [descripcion]) VALUES (3, N'responsableZonas')
INSERT [dbo].[roles] ([idRoles], [descripcion]) VALUES (4, N'callCenter')
INSERT [dbo].[roles] ([idRoles], [descripcion]) VALUES (5, N'administrador')
INSERT [dbo].[roles] ([idRoles], [descripcion]) VALUES (6, N'consulta')
ALTER TABLE [dbo].[actualizacionEstado]  WITH CHECK ADD  CONSTRAINT [fk_actualizacionEstado] FOREIGN KEY([idReclamo])
REFERENCES [dbo].[reclamos] ([idReclamo])
GO
ALTER TABLE [dbo].[actualizacionEstado] CHECK CONSTRAINT [fk_actualizacionEstado]
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
ALTER TABLE [dbo].[facturas]  WITH CHECK ADD  CONSTRAINT [pk_facturas2] FOREIGN KEY([dniCuitCliente])
REFERENCES [dbo].[clientes] ([dniCuit])
GO
ALTER TABLE [dbo].[facturas] CHECK CONSTRAINT [pk_facturas2]
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
